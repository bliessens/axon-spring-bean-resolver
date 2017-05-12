package be.cheops.axer.commands.options;

import org.crsh.cli.Option;
import org.crsh.cli.Usage;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Option(names = {"n", "name"})
@Usage("A readable name")
public @interface NameOption {
}
