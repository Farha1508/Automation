package Base;


public class KpiClass {
    private String kpiTitle;
    private int kpiValueInt;
    private float kpiValueFloat;


    // Initialization for KPIs that use an integer for the value amount
    public KpiClass(String kpiTitle, int kpiValueInt) {
        this.kpiTitle = kpiTitle;
        this.kpiValueInt = kpiValueInt;
    }

    // Initialization for KPIs that use a float for the value amount
    public KpiClass(String kpiTitle, float kpiValueFloat) {
        this.kpiTitle = kpiTitle;
        this.kpiValueFloat = kpiValueFloat;
        // Setting KpiValueInt to -1 to make it easier to tell if a KpiClass item is using a float or int
        this.kpiValueInt = -1;
    }

    public String getKpiTitle() {
        return kpiTitle;
    }

    public int getKpiValueInt() {
        return kpiValueInt;
    }

    public float getKpiValueFloat() {
        return kpiValueFloat;
    }

    public void setKpiValueInt(int kpiValueInt) {
        this.kpiValueInt = kpiValueInt;
    }

    public void setKpiValueFloat(float kpiValueFloat) {
        this.kpiValueFloat = kpiValueFloat;
    }




}
