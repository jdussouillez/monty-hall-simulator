package com.github.jdussouillez.montyhallsim.cmd;

import com.github.jdussouillez.montyhallsim.bean.SwitchStrategy;
import picocli.CommandLine.ITypeConverter;

/**
 * Command line argument converter to create switch strategies
 */
public class SwitchStrategyConverter implements ITypeConverter<SwitchStrategy> {

    @Override
    public SwitchStrategy convert(final String arg) throws Exception {
        if (arg == null || arg.equals("r")) {
            return new SwitchStrategy.Random();
        }
        return switch (arg) {
            case "a" -> new SwitchStrategy.Fixed(true);
            case "n" -> new SwitchStrategy.Fixed(false);
            default -> throw new IllegalArgumentException("Invalid switch strategy");
        };
    }
}
