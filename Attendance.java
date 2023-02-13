import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Attendance extends JFrame {
  private static final long serialVersionUID = 1L;
  private static HashMap<String, Boolean> attendance = new HashMap<>();
  private JTable table;

  public Attendance() {
    super("Attendance Management System");
    setSize(500, 300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    table = new JTable();
    add(new JScrollPane(table), BorderLayout.CENTER);

    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    add(panel, BorderLayout.SOUTH);

    JButton addButton = new JButton("Add");
    addButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String name = JOptionPane.showInputDialog(Attendance.this, "Enter the name:", "Add Attendance", JOptionPane.PLAIN_MESSAGE);
        if (name != null && !name.trim().isEmpty()) {
          attendance.put(name, true);
          updateAttendance();
        }
      }
    });
    panel.add(addButton);

    JButton viewButton = new JButton("View");
    viewButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        updateAttendance();
      }
    });
    panel.add(viewButton);

    setVisible(true);
  }

  private void updateAttendance() {
    String[] columnNames = { "Name", "Attendance" };
    ArrayList<Object[]> data = new ArrayList<>();
    for (String name : attendance.keySet()) {
      Object[] row = { name, attendance.get(name) ? "Present" : "Absent" };
      data.add(row);
    }
    table.setModel(new DefaultTableModel(data.toArray(new Object[0][0]), columnNames));
  }

  public static void main(String[] args) {
    new Attendance();
  }
}
