enum Card {
    STRIKE("打击", 1, 6, "造成6点伤害。"),
    DEFEND("防御", 1, 0, "获得5点格挡。"),
    SURPRISE_SNAKE("惊奇蛇", 2, 12, "造成12点伤害，施加6层剧毒。"),
    TREMOR_BOOM("震颤爆发", 2, 8, "造成8点伤害。如果目标带有震颤，则触发其效果。");
    private final String name;
    private final int cost;
    private final int baseDamage;
    private final String description;
    Card(String name, int cost, int baseDamage, String description) {
        this.name = name;
        this.cost = cost;
        this.baseDamage = baseDamage;
        this.description = description;
    }
    // 公开的方法，让外界可以读取卡牌信息
    public String getName() { return name; }
    public int getCost() { return cost; }
    public int getBaseDamage() { return baseDamage; }
    public String getDescription() { return description; }
    @Override
    public String toString() {
        return name;
    }
}
