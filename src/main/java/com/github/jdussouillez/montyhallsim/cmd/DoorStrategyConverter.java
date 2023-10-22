package com.github.jdussouillez.montyhallsim.cmd;

import com.github.jdussouillez.montyhallsim.Constants;
import com.github.jdussouillez.montyhallsim.bean.DoorStrategy;
import picocli.CommandLine.ITypeConverter;

/**
 * Command line argument converter to create door strategies
 */
public class DoorStrategyConverter implements ITypeConverter<DoorStrategy> {

    @Override
    public DoorStrategy convert(final String arg) throws Exception {
        if (arg == null || arg.equals("r")) {
            return new DoorStrategy.Random();
        }
        var door = Integer.parseInt(arg);
        if (door < 0 || door >= Constants.NB_DOORS) {
            throw new IllegalArgumentException("Invalid door number");
        }
        return new DoorStrategy.Fixed(door);
    }
}
