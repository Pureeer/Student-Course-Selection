package com.student.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import com.student.AppConstants;
import com.student.dao.AdminDAO;
import com.student.model.Course;

/**
 * @Description: AdminView
 * @ClassName: AdminView
 * 
 */
public class AdminView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JMenuBar menuBar;
    private JPanel contentPane;
    private JComboBox<String> course;
    private Vector<Course> courses;
    private JLabel coursename, teachername;
    private JTable gradetable;
    private static String[] infocolumn = { AppConstants.SNO, AppConstants.SCORE };
    private JButton querybtn;

    public static void main(String[] args) {
        AdminView test = new AdminView();

    }

    public AdminView() {
        System.out.println("Admin Login Success.");

        setResizable(false);
        setTitle(AppConstants.ADMIN_TITLE);
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(5, 5));

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(0, 2, 0, 0));

        JPanel courepanel = new JPanel();
        panel.add(courepanel);
        courepanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        JLabel courselabel = new JLabel(AppConstants.ADMIN_CNAME);
        courepanel.add(courselabel);

        coursename = new JLabel();
        courepanel.add(coursename);

        JPanel teacherpanel = new JPanel();
        panel.add(teacherpanel);
        teacherpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        JLabel teacherlabel = new JLabel(AppConstants.ADMIN_TNAME);
        teacherpanel.add(teacherlabel);

        teachername = new JLabel();
        teacherpanel.add(teachername);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("admin Logout.");
                dispose();
                new LoginView();
            }
        });

        initMenu();
        initChoose();
        initGrade();
        initBtn();
    }

    private void initMenu() {
        JMenu maintain = new JMenu(AppConstants.ADMIN_MAINTAIN);
        menuBar.add(maintain);

        JMenuItem courseinfo = new JMenuItem(AppConstants.ADMIN_COURSEINFO);
        courseinfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
        maintain.add(courseinfo);

        JMenuItem studentinfo = new JMenuItem(AppConstants.ADMIN_STUDENTINFO);
        studentinfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
        maintain.add(studentinfo);

        // TODO: Table maintain
    }

    private void initChoose() {
        System.err.println("Loading Choose Box...");
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.WEST);
        panel.setLayout(new BorderLayout(0, 0));

        panel.add(new JLabel(AppConstants.ADMIN_CHOOSE), BorderLayout.NORTH);

        JPanel choosebox = new JPanel();
        panel.add(choosebox);

        course = new JComboBox<>();
        course.setPreferredSize(new Dimension(100, 20));
        choosebox.add(course);

        String[][] result = AdminDAO.getInstance().getAllCourse();
        courses = new Vector<>();

        for (int i = 0; i < result.length; i++) {
            Course c = new Course(result[i][0]);
            c.setCname(result[i][1]);
            c.setCredit(Integer.parseInt(result[i][2]));
            c.setCdept(result[i][3]);
            c.setTname(result[i][4]);
            courses.add(c);
            course.addItem(c.getCname());
        }
        course.setSelectedIndex(-1);
    }

    private void initGrade() {
        System.err.println("Loading Score Info...");
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(5, 5));

        panel.add(new JLabel(AppConstants.ADMIN_SELECTIONINFO), BorderLayout.NORTH);

        gradetable = new JTable();
        gradetable.setEnabled(false);

        initGradeTable(gradetable, null, infocolumn);
        JScrollPane scrollPane = new JScrollPane(gradetable);
        gradetable.getTableHeader().setReorderingAllowed(false);
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    private void initBtn() {
        System.err.println("Loading Buttons...");
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.EAST);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        querybtn = new JButton(AppConstants.ADMIN_QUERY);
        querybtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        JToggleButton inputbtn = new JToggleButton(AppConstants.ADMIN_INPUT);
        inputbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputbtn.setEnabled(false);
        JButton exitbtn = new JButton(AppConstants.ADMIN_CLOSE);
        exitbtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createRigidArea(new Dimension(100, 30)));
        panel.add(querybtn);
        panel.add(Box.createRigidArea(new Dimension(100, 30)));
        panel.add(inputbtn);
        panel.add(Box.createRigidArea(new Dimension(100, 30)));
        panel.add(exitbtn);

        inputbtn.addActionListener(new InputListener());
        querybtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int index = course.getSelectedIndex();
                try {
                    coursename.setText(courses.get(index).getCname());
                    teachername.setText(courses.get(index).getTname());
                    String[][] result = AdminDAO.getInstance().queryStuWhoSeleCou(courses.get(index).getCno());
                    initGradeTable(gradetable, result, infocolumn);
                    inputbtn.setEnabled(true);
                } catch (ArrayIndexOutOfBoundsException e2) {
                    // Do nothing...
                }
            }
        });
        exitbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("admin Logout.");
                dispose();
                new LoginView();
            }
        });
        getRootPane().setDefaultButton(querybtn);
    }

    private void initGradeTable(JTable jTable, String[][] result, String[] column) {
        jTable.setModel(new DefaultTableModel(result, column) {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
        });
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    private class InputListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JToggleButton btn = (JToggleButton) e.getSource();
            if (btn.isSelected()) {
                System.out.println("Edit students' grade");
                gradetable.setEnabled(true);
                btn.setText(AppConstants.ADMIN_SAVE);
                course.setEnabled(false);
                querybtn.setEnabled(false);
            } else {
                save(gradetable);
                System.out.println("Update students' grade");
                update();
                gradetable.setEnabled(false);
                btn.setText(AppConstants.ADMIN_INPUT);
                ;
                course.setEnabled(true);
                querybtn.setEnabled(true);
            }
        }

        private void save(JTable table) {
            if (table.isEditing()) {
                TableCellEditor cellEditor = table.getCellEditor();
                if (cellEditor != null) {
                    cellEditor.stopCellEditing();
                }
            }
        }

        private void update() {
            int index = course.getSelectedIndex();
            int row = gradetable.getRowCount();
            String cno = courses.get(index).getCno();
            for (int i = 0; i < row; i++) {
                String sno = (String) gradetable.getValueAt(i, 0);
                String grade = (String) gradetable.getValueAt(i, 1);
                try {
                    Integer.parseInt(grade);
                } catch (NumberFormatException e) {
                    grade = null;
                    gradetable.setValueAt(grade, i, 1);
                }
                AdminDAO.getInstance().updateCourseGrade(sno, cno, grade);
            }
        }
    }
}
