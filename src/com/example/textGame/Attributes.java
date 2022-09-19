package com.example.textGame;

public enum Attributes {
    //NEGATIVE
    RUSTY(0.85f, 0.6f, 0.85f),
    BROKEN(0.6f, 0.3f, 0.85f),
    MEAGER(0.8f, 1f, 1f),
    FEEBLE(0.8f, 0.8f, 1f),
    BOOMER(3f, 5f, 0.2f),
    WRETCHED(0.8f, 0.55f, 0.75f),
    SMELLY(0.95f, 1f, 1f),

    //NEUTRAL
    SUSPICIOUSLY_MEDIOCRE(1f, 2f, 1f),
    AVERAGE(1f, 1f, 1f),

    //POSITIVE
    PLEASANT(1.2f, 1.1f, 1.2f),
    ENHANCED(1.25f, 1.3f, 1.2f),
    MIGHTY(1.3f, 14f, 0.95f),
    LEGENDARY(1.5f, 1.5f, 1.35f),
    CELESTIAL(1.6f, 2f, 1.7f),
    BLOODTHIRSTY(1.2f, 1.25f, 1.6f),
    GODLY(1.75f, 2f, 1.8f),
    HEAVY(1.4f, 1.5f, 0.8f),
    GEN_Z(0.3f, 1f, 3f);

    float damageModifier;
    float durabilityModifier;
    float attackSpeedModifier;

    Attributes(float damageModifier, float durabilityModifier, float attackSpeedModifier) {
        this.damageModifier = damageModifier;
        this.durabilityModifier = durabilityModifier;
        this.attackSpeedModifier = attackSpeedModifier;
    }

    public static Attributes chooseAttribute() {
        Attributes returnValue;
        int randomValue = (int) Math.round(Math.random()*1000);
        if (randomValue > 980) { returnValue = GEN_Z; } else //2%
        if (randomValue > 960) { returnValue = BOOMER; } else //2%
        if (randomValue > 930) { returnValue = GODLY; } else //3%
        if (randomValue > 890) { returnValue = CELESTIAL; } else //4%
        if (randomValue > 840) { returnValue = LEGENDARY; } else //5%
        if (randomValue > 780) { returnValue = BLOODTHIRSTY; } else //6%
        if (randomValue > 720) { returnValue = MIGHTY; } else //6%
        if (randomValue > 650) { returnValue = HEAVY; } else //7%
        if (randomValue > 580) { returnValue = ENHANCED; } else //7%
        if (randomValue > 490) { returnValue = PLEASANT; } else //9%
        if (randomValue > 410) { returnValue = AVERAGE; } else //8%
        if (randomValue > 330) { returnValue = SUSPICIOUSLY_MEDIOCRE; } else //8%
        if (randomValue > 240) { returnValue = SMELLY; } else //9%
        if (randomValue > 170) { returnValue = MEAGER; } else //7%
        if (randomValue > 110) { returnValue = FEEBLE; } else //6%
        if (randomValue > 60) { returnValue = WRETCHED; } else //5%
        if (randomValue > 20) { returnValue = RUSTY; } else //4%
        { returnValue = BROKEN; } //2%
        return returnValue;
    }
}
