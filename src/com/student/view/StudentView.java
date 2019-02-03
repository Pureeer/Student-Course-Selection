package com.student.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import com.student.AppConstants;
import com.student.dao.StudentDAO;
import com.student.model.Student;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * @Description: Student Select Course View
 * @ClassName: StudentView
 * 
 */
public class StudentView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Student student;
    private JTable infotable, coursetable, scoretable;
    private static String[] infocolumn = {AppConstants.SNO, AppConstants.SNAME, AppConstants.SEX,
            AppConstants.AGE, AppConstants.SDEPT};
    private static String[] coursecolumn = {AppConstants.CNO, AppConstants.CNAME,
            AppConstants.CREDIT, AppConstants.CDEPT, AppConstants.TNAME};
    private static String[] scorecolumn =
            {AppConstants.CNO, AppConstants.CNAME, AppConstants.SCORE};
    private JTextField textField;

    // TODO: Other Table Column

    public StudentView(Student student) {
        this.student = student;
        setResizable(false);
        setTitle(AppConstants.STUDENT_TITLE);
        setBounds(100, 100, 800, 400);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                new LoginView();
            }
        });

        setVisible(true);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        JPanel btnpanel = new JPanel();
        contentPane.add(btnpanel, BorderLayout.EAST);
        btnpanel.setLayout(new BoxLayout(btnpanel, BoxLayout.Y_AXIS));

        JButton selectbtn = new JButton(AppConstants.STUDENT_SELECT);
        selectbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton dropbtn = new JButton(AppConstants.STUDENT_DROP);
        dropbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton closebtn = new JButton(AppConstants.STUDENT_CLOSE);
        closebtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        selectbtn.addActionListener(new SelectListener());
        dropbtn.addActionListener(new DropListener());
        closebtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginView();

            }
        });

        btnpanel.add(Box.createRigidArea(new Dimension(100, 50)));
        btnpanel.add(selectbtn);
        btnpanel.add(Box.createRigidArea(new Dimension(100, 50)));
        btnpanel.add(dropbtn);
        btnpanel.add(Box.createRigidArea(new Dimension(100, 50)));
        btnpanel.add(closebtn);

        JPanel centerpanel = new JPanel();
        contentPane.add(centerpanel, BorderLayout.CENTER);
        centerpanel.setLayout(new GridLayout(2, 2, 15, 15));

        initInfo(centerpanel);
        initCourse(centerpanel);
        initScore(centerpanel);
        initSelect(centerpanel);
    }

    private void initInfo(JPanel centerpanel) {
        System.err.println("Loading Student Info...");
        JPanel panel = new JPanel();
        centerpanel.add(panel);
        panel.setLayout(new BorderLayout());

        JPanel label = new JPanel();
        panel.add(label, BorderLayout.NORTH);
        label.add(new JLabel(AppConstants.STUDENT_INFO));

        infotable = new JTable();
        infotable.setEnabled(false);
        String[][] result = StudentDAO.getInstance().queryStudent(student.getSno());

        // Assign the information.
        student.setSname(result[0][1]);
        student.setSex(result[0][2]);
        try {
            // Maybe the age is NULL
            student.setAge(Integer.parseInt(result[0][3]));
        } catch (NumberFormatException e) {
            student.setAge(-1);
        }
        student.setSdept(result[0][4]);
        student.setUsername(result[0][5]);

        initTable(infotable, result, infocolumn);
        JScrollPane scrollpane = new JScrollPane(infotable);
        infotable.getTableHeader().setReorderingAllowed(false);
        panel.add(scrollpane, BorderLayout.CENTER);
    }

    private void initCourse(JPanel centerpanel) {
        System.err.println("Loading Course Info...");
        JPanel panel = new JPanel();
        centerpanel.add(panel);
        panel.setLayout(new BorderLayout());

        JPanel mainpanel = new JPanel();
        panel.add(mainpanel, BorderLayout.CENTER);
        mainpanel.setLayout(new BorderLayout());

        JPanel label = new JPanel();
        mainpanel.add(label, BorderLayout.NORTH);

        JLabel courselabel = new JLabel(AppConstants.STUDENT_COURSE);
        label.add(courselabel);

        coursetable = new JTable();
        coursetable.setEnabled(false);

        String[][] result = StudentDAO.getInstance().queryCourses(student.getSno());

        initTable(coursetable, result, coursecolumn);
        JScrollPane scrollPane = new JScrollPane(coursetable);
        coursetable.getTableHeader().setReorderingAllowed(false);
        mainpanel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputpanel = new JPanel();
        panel.add(inputpanel, BorderLayout.SOUTH);

        inputpanel.add(new JLabel(AppConstants.STUDENT_INPUT));
        textField = new JTextField();
        inputpanel.add(textField);
        textField.setColumns(10);
    }


    private void initScore(JPanel centerpanel) {
        System.err.println("Loading Score Info...");
        JPanel panel = new JPanel();
        centerpanel.add(panel);
        panel.setLayout(new BorderLayout());

        JPanel label = new JPanel();
        panel.add(label, BorderLayout.NORTH);
        label.add(new JLabel(AppConstants.STUDENT_SCORE));

        scoretable = new JTable();
        scoretable.setEnabled(false);
        String[][] result = StudentDAO.getInstance().queryStuGrade(student.getSno());

        initTable(scoretable, result, scorecolumn);
        JScrollPane scrollpane = new JScrollPane(scoretable);
        scoretable.getTableHeader().setReorderingAllowed(false);
        panel.add(scrollpane);

    }

    private void initSelect(JPanel centerpanel) {
        JPanel selectedpanel = new JPanel();
        centerpanel.add(selectedpanel);

        // TODO: Select Courses.
    }

    private void initTable(JTable jTable, String[][] result, String[] column) {
        jTable.setModel(new DefaultTableModel(result, column));
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    private class SelectListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

        }
    }

    private class DropListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

        }
    }

}
