package me.tiezhu.game.puzzle.chess;

import static org.apache.commons.lang3.builder.ToStringStyle.SIMPLE_STYLE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.math.NumberUtils;

import me.tiezhu.game.puzzle.common.Coordinate2D;

/**
 * 日历puzzle的棋盘
 * 特点
 * 1.边缘异形
 * 2.棋盘背景有字（尊重实物所以字写在棋盘背景）
 * 3.需要提前覆盖透明的block，且覆盖有日期校验逻辑（防止解不出来）
 *
 * @author liushuai
 * Created on 2022/2/10
 */
public class CalenderPuzzleChess extends AbstractChess2D<CalenderPuzzleChess.CalenderBlock> {

    private final List<CalenderBlock> initConfigList;

    // 保存实际有用的block
    private Map<Coordinate2D, CalenderBlock> coordinateToBlockMap;
    // 保存日期值和坐标的关系
    private Map<CalenderDateValue, CalenderBlock> dateValueToCoordinateMap;

    /**
     * 输入是所有有字格子配置行
     * @param configs
     */
    public CalenderPuzzleChess(List<String> configs) {
        this.initConfigList = configs.stream()
                .map(this::parseConfig)
                .collect(Collectors.toList());
    }

    private CalenderBlock parseConfig(String line) {
        String[] parts = line.split(" ");

        int x = NumberUtils.toInt(parts[0]);
        int y = NumberUtils.toInt(parts[1]);
        String valueInput = parts[2];
        int value = NumberUtils.toInt(valueInput);

        if (value > 0) {
            return new CalenderBlock(x, y, new CalenderDateValue(CalenderBlockType.Date, value));
        } else {
            return new CalenderBlock(x, y, new CalenderDateValue(
                    CalenderBlockType.Month, PuzzleMonth.valueOf(valueInput).getValue()));
        }
    }

    /**
     * 重新初始化棋盘
     */
    public void initChess() {
        int x = initConfigList.stream().map(CalenderBlock::getX).max(Integer::compareTo).get() + 1;
        int y = initConfigList.stream().map(CalenderBlock::getY).max(Integer::compareTo).get() + 1;

        // super方棋盘
        super.chessSquare = new Chess2DBlock[x][y];
        // 日历棋盘
        coordinateToBlockMap = new HashMap<>();
        dateValueToCoordinateMap = new HashedMap<>();

        // 日历背景格子赋值
        initConfigList.forEach(block -> {
            chessSquare[block.getX()][block.getY()] = new Chess2DBlock(block.getX(), block.getY(), BlockStatus.UNSET);
            coordinateToBlockMap.put(block.toCoordinate2D(), block);
            dateValueToCoordinateMap.put(block.getDateValue(), block);
        });

        // 异形部分赋值
        for (int i = 0;i < chessSquare.length; i++) {
            Chess2DBlock[] line = chessSquare[i];
            for (int j = 0;j < line.length; j++) {
                if (line[j] == null) {
                    line[j] = new Chess2DBlock(i, j, BlockStatus.OUT);
                }
            }
        }
    }

    /**
     * 特定逻辑，标记日期block并且将其设置成透明（显示棋盘背景）
     * @param inputDate
     */
    public void setInitDate(PuzzleDate inputDate) {
        if (!inputDate.getMonth().validateDate(inputDate.getDateValue())) {
            throw new IllegalArgumentException("bad date " + inputDate);
        }

        // set month block
        CalenderDateValue monthValue = new CalenderDateValue(CalenderBlockType.Month, inputDate.getMonth().getValue());
        CalenderBlock monthBlock = dateValueToCoordinateMap.get(monthValue);
        setBlock(monthBlock);
        monthBlock.setBlockMaskType(CalenderBlockMaskType.HYALINE);
        // set date block
        CalenderDateValue dateValue = new CalenderDateValue(CalenderBlockType.Date, inputDate.getDateValue());
        CalenderBlock dateBlock = dateValueToCoordinateMap.get(dateValue);
        setBlock(dateBlock);
        dateBlock.setBlockMaskType(CalenderBlockMaskType.HYALINE);
    }

    @Override
    protected CalenderBlock toC(Chess2DBlock block) {
        return coordinateToBlockMap.get(block);
    }

    public static class CalenderBlock extends Coordinate2D {
        private CalenderDateValue dateValue;
        private CalenderBlockMaskType blockMaskType;

        public CalenderBlock(int x, int y, CalenderDateValue dateValue) {
            super(x, y);
            this.dateValue = dateValue;
        }

        public CalenderDateValue getDateValue() {
            return dateValue;
        }

        public CalenderBlockMaskType getBlockMaskType() {
            return ObjectUtils.defaultIfNull(blockMaskType, CalenderBlockMaskType.COVER);
        }

        public void setBlockMaskType(CalenderBlockMaskType blockMaskType) {
            this.blockMaskType = blockMaskType;
        }

        public Coordinate2D toCoordinate2D() {
            return new Coordinate2D(getX(), getY());
        }

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this);
        }
    }

    public enum CalenderBlockType {
        Month,
        Date,
        Weekday // 后续改造成国产款（不可能）
    }

    public enum CalenderBlockMaskType {
        HYALINE, // 透明
        COVER
    }

    public enum PuzzleMonth {
        Jan(31),
        Feb(29),
        Mar(31),
        Apr(30),
        May(31),
        Jun(30),
        Jul(31),
        Aug(31),
        Sep(30),
        Oct(31),
        Nov(30),
        Dec(31);

        private final int maxDateValue;

        PuzzleMonth(int maxDateValue) {
            this.maxDateValue = maxDateValue;
        }

        public int getValue() {
            return ordinal();
        }

        public static PuzzleMonth fromMonthValue(int value) {
            return values()[value];
        }

        public boolean validateDate(int dateInput) {
            return dateInput > 0 && dateInput <= maxDateValue;
        }
    }

    public static class PuzzleDate {
        PuzzleMonth month;
        int dateValue;

        public PuzzleDate(PuzzleMonth month, int dateValue) {
            this.month = month;
            this.dateValue = dateValue;
        }

        public PuzzleMonth getMonth() {
            return month;
        }

        public int getDateValue() {
            return dateValue;
        }

        @Override
        public String toString() {
            return month + " " + dateValue;
        }
    }

    public static class CalenderDateValue {
        private CalenderBlockType blockType;
        private int value;

        public CalenderDateValue(CalenderBlockType blockType, int value) {
            this.blockType = blockType;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (!(o instanceof CalenderDateValue)) return false;

            CalenderDateValue that = (CalenderDateValue) o;

            return new EqualsBuilder().append(value, that.value).append(blockType, that.blockType).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(blockType).append(value).toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, SIMPLE_STYLE)
                    .append("blockType", blockType)
                    .append("value", value)
                    .toString();
        }
    }
}
