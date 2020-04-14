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
public class ZipSteganography extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnEncrypt;
	private JButton btnDecrypt;
	private JButton btnData;
	private JButton btnVideoFile;

	String DataFile,VideoFile,FinalFile,file_name;
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
					ZipSteganography frame = new ZipSteganography();
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
	public ZipSteganography() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VideoSteganography.class.getResource("/source/hackerb.jpg")));
		setTitle("Zip Steganography");
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
			textField.setText(file_name);

			}
		});
		btnData.setBounds(48, 219, 89, 37);
		contentPane.add(btnData);

		btnVideoFile = new JButton("Video file");
		btnVideoFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VideoFile = selectFile();
				textField_1.setText(file_name);

				
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

		JLabel label = new JLabel("Steganography Using Video zipping");
		label.setForeground(new Color(255, 127, 80));
		label.setFont(new Font("Algerian", Font.BOLD, 35));
		label.setBounds(151, 13, 565, 78);
		contentPane.add(label);
		
		
	}

	//method to select file
	String selectFile(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    file_name = selectedFile.getName();
		    return selectedFile.getAbsolutePath();
		    
		}
		return null;
	}




	//start of zipfile method
	public void zipFiles(String Text,String Video) {


		new File("C:\\Video Steganography\\Encryted Files").mkdirs();
        String zipFile = "C:\\Video Steganography\\Encryted Files\\encrypted_file.mp4";

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
