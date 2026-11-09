public class UnitTranslation {
    private int unitId;
    private String name;
    private String description;

    public UnitTranslation(
            int unitId,
            String name,
            String description){
        this.unitId = unitId;
        this.name = name;
        this.description = description;
    }

    public int getUnitId(){
        return unitId;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    @Override
    public String toString(){
        return name;
    }
}
