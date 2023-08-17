package strategies;

import enums.SlotAllotmentStrategyType;

public class SlotAssignmentStrategyFactory {
    public static SlotAssignmentStrategy getSlotAssignmentStrategy(SlotAllotmentStrategyType type){
        return new RandomSlotAssignmentStrategy();
    }
}
