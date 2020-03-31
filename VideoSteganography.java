package source;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class VideoSteganography extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnEncrypt;
	private JButton btnDecrypt;
	private JButton btnData;
	private JButton btnVideoFile;

	String DataFile,VideoFile,FinalFile,file1,file2;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	protected Component frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VideoSteganography frame = new VideoSteganography();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VideoSteganography() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VideoSteganography.class.getResource("/source/hackerb.jpg")));
		setTitle("Video Steganography");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 877, 560);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 205, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setToolTipText("Opened filename");
		textField.setBounds(48, 165, 89, 28);
		contentPane.add(textField);
		textField.setColumns(10);

		btnData = new JButton("Data");
		btnData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			DataFile = selectFile();
			textField.setText(file1);

			}
		});
		btnData.setBounds(48, 219, 89, 37);
		contentPane.add(btnData);

		btnVideoFile = new JButton("Video file");
		btnVideoFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VideoFile = selectFile();
				textField_1.setText(file1);

				
			}
		});
		btnVideoFile.setBounds(217, 219, 89, 37);
		contentPane.add(btnVideoFile);

		textField_1 = new JTextField();
		textField_1.setToolTipText("Opened filename\r\n");
		textField_1.setColumns(10);
		textField_1.setBounds(220, 165, 86, 28);
		contentPane.add(textField_1);

		btnEncrypt = new JButton("Encrypt");
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			try {
				zipFiles(DataFile,VideoFile);
				
				JOptionPane.showMessageDialog(frame, "Your files were encrypted");
			}
			catch(Exception ee) {
				JOptionPane.showMessageDialog(frame, "No files were selected!!!");
			}
			
			}
		});
		btnEncrypt.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEncrypt.setBounds(87, 316, 193, 78);
		contentPane.add(btnEncrypt);

		btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unzipFiles();
				JOptionPane.showMessageDialog(frame, "Your files were decrypted");
			}
		});
		btnDecrypt.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDecrypt.setBounds(87, 407, 194, 78);
		contentPane.add(btnDecrypt);

		JLabel label = new JLabel("Steganography Using Video");
		label.setForeground(new Color(255, 127, 80));
		label.setFont(new Font("Algerian", Font.BOLD, 35));
		label.setBounds(151, 13, 565, 78);
		contentPane.add(label);
		
		lblNewLabel = new JLabel("By-");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(531, 97, 56, 28);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Nikhil Wagh");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(623, 138, 150, 28);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Akanksha Gaikwad");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(623, 188, 181, 28);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Tejal Pawar");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(623, 241, 150, 28);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Vaishali Chothe");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4.setBounds(623, 300, 150, 28);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Project Guide -");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_5.setBounds(486, 415, 143, 28);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Rohit Kautkar");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_6.setBounds(635, 406, 212, 47);
		contentPane.add(lblNewLabel_6);
	}

	//method to select file
	String selectFile(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    file1 = selectedFile.getName();
		    return selectedFile.getAbsolutePath();
		    
		}
		return null;
	}




	//start of zipfile method
	public void zipFiles(String Text,String Video) {


		new File("E:\\Video Steganography\\Encryted Files").mkdirs();
        String zipFile = "E:\\Video Steganography\\Encryted Files\\new.mp4";

        String[] srcFiles = { Video, Text};

        try {

            // create byte buffer
            byte[] buffer = new byte[1024];

            FileOutputStream fos = new FileOutputStream(zipFile);

            ZipOutputStream zos = new ZipOutputStream(fos);

            for (int i=0; i < srcFiles.length; i++) {

                File srcFile = new File(srcFiles[i]);

                FileInputStream fis = new FileInputStream(srcFile);

                // begin writing a new ZIP entry, positions the stream to the start of the entry data
                zos.putNextEntry(new ZipEntry(srcFile.getName()));

                int length;

                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }

                zos.closeEntry();

                // close the InputStream
                fis.close();

            }

            // close the ZipOutputStream
            zos.close();

        }
        catch (IOException ioe) {
        	System.out.println(DataFile+"\n"+VideoFile);
        	System.out.println("Error creating zip file: " + ioe);
        }

    }

//end of zip file

//start of unzipfile
public void unzipFiles() {

	JFileChooser fileChooser = new JFileChooser();
	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
	int result = fileChooser.showOpenDialog(this);
	if (result == JFileChooser.APPROVE_OPTION) {
	    File selectedFile = fileChooser.getSelectedFile();

        String zipFilePath = selectedFile.getAbsolutePath();

        new File("E:\\Video Steganography\\Extracted Data").mkdirs();
        String destDir = "E:\\Video Steganography\\Extracted Data";

        unzip(zipFilePath, destDir);

    }
}
    private static void unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while(ze != null){
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                //System.out.println("Unzipping to "+newFile.getAbsolutePath());
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
        	///System.out.println(textfile);
            e.printStackTrace();
        }

    }
}
