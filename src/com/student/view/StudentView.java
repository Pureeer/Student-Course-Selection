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
import javax.swing.JSplitPane;

/**
 * @Description: Student Select Course View
 * @ClassName: StudentView
 * 
 */
public class StudentView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Student student;
    private JTable infotable, coursetable;
    private static String[] infocolumn = {AppConstants.SNO, AppConstants.SNAME, AppConstants.SEX,
            AppConstants.AGE, AppConstants.SDEPT};
    private static String[] coursecolumn =
            {AppConstants.CNO, AppConstants.CNAME, AppConstants.CREDT, AppConstants.TNAME};
    private JTextField textField;
    // TODO: Other Table Column

    public StudentView(Student student) {
        this.student = student;
        setResizable(false);
        setTitle(AppConstants.STUDENT_TITLE);
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                new LoginView();
            }
        });
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

        // TODO: Other Table
        JPanel scorepanel = new JPanel();
        centerpanel.add(scorepanel);

        JPanel selectedpanel = new JPanel();
        centerpanel.add(selectedpanel);

        setVisible(true);
    }


    private void initInfo(JPanel centerpanel) {
        JPanel infopanel = new JPanel();
        centerpanel.add(infopanel);
        infopanel.setLayout(new BorderLayout());
        JPanel infolabelpanel = new JPanel();
        infopanel.add(infolabelpanel, BorderLayout.NORTH);
        infolabelpanel.add(new JLabel(AppConstants.STUDENT_INFO));

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
        JScrollPane infotablepanel = new JScrollPane(infotable);
        infotable.getTableHeader().setReorderingAllowed(false);
        infopanel.add(infotablepanel, BorderLayout.CENTER);
    }

    private void initCourse(JPanel centerpanel) {
        JPanel coursepanel = new JPanel();
        centerpanel.add(coursepanel);
        coursepanel.setLayout(new BorderLayout());

        JPanel mainpanel = new JPanel();
        coursepanel.add(mainpanel, BorderLayout.CENTER);
        mainpanel.setLayout(new BorderLayout());

        JPanel mainlabelpanel = new JPanel();
        mainpanel.add(mainlabelpanel, BorderLayout.NORTH);

        JLabel courselabel = new JLabel(AppConstants.STUDENT_COURSE);
        mainlabelpanel.add(courselabel);

        coursetable = new JTable();
        coursetable.setEnabled(false);

        // TODO: The query.
        String[][] result = null;
        
        initTable(coursetable, result, coursecolumn);
        JScrollPane scrollPane = new JScrollPane(coursetable);
        infotable.getTableHeader().setReorderingAllowed(false);
        mainpanel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputpanel = new JPanel();
        coursepanel.add(inputpanel, BorderLayout.SOUTH);
        
        inputpanel.add( new JLabel(AppConstants.STUDENT_INPUT));
        textField = new JTextField();
        inputpanel.add(textField);
        textField.setColumns(10);

    }

    private void initTable(JTable jTable, String[][] result, String[] column) {
        jTable.setModel(new DefaultTableModel(result, column));
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }
}
