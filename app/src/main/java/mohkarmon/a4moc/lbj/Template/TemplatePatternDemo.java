package mohkarmon.a4moc.lbj.Template;

import mohkarmon.a4moc.lbj.Models.Category;
import mohkarmon.a4moc.lbj.Models.Item;

public class TemplatePatternDemo {
    public static void main(String[] args) {

        Data data = new Category();
        data.AddData();
        Data data1 = new Item();
        data1.AddData();
    }
}
