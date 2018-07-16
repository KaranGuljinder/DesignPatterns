package mohkarmon.a4moc.lbj.Template;

public abstract class Data {
    public abstract int getId();
    private int id;
    private String name;
    protected abstract void setId(int id);
    protected abstract String getName();
    public abstract void setName(String name);


    public final void AddData() {
        int id =  getId();
        setId(id);
        String name = getName();
        setName(name);
    }
}