package common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Timeouts {
    NULL(0),
    TECHNICAL(1000),
    MIN(4000),
    TINY(10000),
    SMALL(15000),
    MEDIUM(30000),
    BIG(60000),
    HUGE(90000),
    MAX(150000);

    private final int milliseconds;
}
