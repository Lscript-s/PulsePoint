import javax.swing.*;
import java.awt.*;

public class ExamineeRecord extends PPanel{
    private PLabel lblID = new PLabel(), lblName = new PLabel(), lblRole = new PLabel();
    private PButton btnView = new PButton("View Record");

    // Dapat pantay lahat, parang sa canva prototype nio
    // Assign a layout
    // Add Border Colors, Radius
    // Add distinct borders to labels
    // Add components to the panel using add()
    // Gawa kayo ng getter para sa button
    ExamineeRecord(String id, String name, String role){
        lblID.setText(id);
        lblName.setText(name);
        lblRole.setText(role);

        // Pwede nio to iremove/ibahin kapag may layout na kau, pang debug lng yan
        add(lblID);
        add(lblName);
        add(lblRole);
        add(btnView);
    }

    public static void main(String[] args) {
        JFrame frm = new JFrame();
        frm.setDefaultCloseOperation(3);
        frm.setSize(500,500);
        frm.setLayout(new GridLayout(10,1));

        ExamineeRecord rec = new ExamineeRecord("2023-12345", "Doe, John A.", "Student");
        ExamineeRecord rec2 = new ExamineeRecord("2023-12346", "Jane, Mary B.", "Employee");
        ExamineeRecord rec3 = new ExamineeRecord("2025-12347", "Aldous, Gusion", "Student");

        frm.add(rec);
        frm.add(rec2);
        frm.add(rec3);

        frm.setVisible(true);
    }

}
