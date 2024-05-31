import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        new ImageViewerGUI();
    }
}
class ImageViewerGUI extends JFrame implements ActionListener {
    JButton selectFileButton;
    JButton showImageButton;
    JButton resizeButton;
    JButton grayscaleButton;
    JButton brightnessButton;
    JButton closeButton;
    JButton showResizeButton;
    JButton showBrightnessButton;
    JButton backButton;
    JTextField widthTextField;
    JTextField heightTextField;
    JTextField brightnessTextField;
    String filePath = "C:\\Users\\VAIO\\Downloads\\photo";
    File file;
    JFileChooser fileChooser = new JFileChooser(filePath);
    int h = 900;
    int w = 1200;
    float brightenFactor = 1;

    ImageViewerGUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Image Viewer");
        this.setSize(700, 300);
        this.setVisible(true);
        this.setResizable(true);

        mainPanel();
    }

    public void mainPanel(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 2));
        buttonsPanel.setBounds(200,50,300,150);
        selectFileButton = new JButton("Choose Image");
        selectFileButton.addActionListener(this);
        showImageButton = new JButton("Show Image");
        showImageButton.addActionListener(this);
        brightnessButton = new JButton("Brightness");
        brightnessButton.addActionListener(this);
        grayscaleButton = new JButton("Gray scale");
        grayscaleButton.addActionListener(this);
        resizeButton = new JButton("Resize");
        resizeButton.addActionListener(this);
        closeButton = new JButton("Exit");
        closeButton.addActionListener(this);
        buttonsPanel.add(this.selectFileButton);
        buttonsPanel.add(this.showImageButton);
        buttonsPanel.add(this.brightnessButton);
        buttonsPanel.add(this.grayscaleButton);
        buttonsPanel.add(this.resizeButton);
        buttonsPanel.add(this.closeButton);

        mainPanel.add(buttonsPanel);
        this.add(mainPanel);
    }
    public void chooseFileImage(){
        fileChooser.setAcceptAllFileFilterUsed(false);
        int option = fileChooser.showOpenDialog(ImageViewerGUI.this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File givenFile = fileChooser.getSelectedFile();
            // check the format of file to be image file
            String format = givenFile.getName().substring(givenFile.getName().lastIndexOf('.')+1);
            if(format.equals("jpg") || format.equals("png")){
                file = fileChooser.getSelectedFile();
            }
            else {
                file = null;
                JLabel jLabel = new JLabel("Wrong file type!");
                jLabel.setBounds(300,180,100,100);
                this.add(jLabel);
            }
        }
        this.mainPanel();

    }
    public void brightnessPanel(){
        JPanel brightnessPanel = new JPanel();
        brightnessPanel.setLayout(null);

        brightnessTextField = new JTextField();
        brightnessTextField.setBounds(300,100,150,25);
        brightnessPanel.add(brightnessTextField);

        JLabel brightnessLabel = new JLabel("brightness:");
        brightnessLabel.setBounds(225,100,100,20);
        brightnessPanel.add(brightnessLabel);

        showBrightnessButton = new JButton("Show Result");
        backButton = new JButton("Back");
        showBrightnessButton.setBounds(150,200,120,40);
        backButton.setBounds(450,200,120,40);
        showBrightnessButton.addActionListener(this);
        backButton.addActionListener(this);
        brightnessPanel.add(showBrightnessButton);
        brightnessPanel.add(backButton);

        this.add(brightnessPanel);
    }
    public void resizePanel(){
        JPanel resizePanel = new JPanel();
        resizePanel.setLayout(null);

        heightTextField = new JTextField();
        widthTextField = new JTextField();

        heightTextField.setBounds(300,100,150,25);
        widthTextField.setBounds(300,125,150,25);
        resizePanel.add(heightTextField);
        resizePanel.add(widthTextField);

        JLabel heightLabel = new JLabel("height:");
        JLabel widthLabel = new JLabel("width:");
        heightLabel.setBounds(250,100,100,20);
        widthLabel.setBounds(250,125,100,20);
        resizePanel.add(heightLabel);
        resizePanel.add(widthLabel);

        showResizeButton = new JButton("Show Result");
        backButton = new JButton("Back");
        showResizeButton.setBounds(150,200,120,40);
        backButton.setBounds(450,200,120,40);
        showResizeButton.addActionListener(this);
        backButton.addActionListener(this);
        resizePanel.add(showResizeButton);
        resizePanel.add(backButton);

        this.add(resizePanel);
    }

    public void showimage(){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        JLabel tempLabel = new JLabel();

        ImageIcon imageIcon = new ImageIcon(new ImageIcon(String.valueOf(file)).getImage());
        tempLabel.setIcon(imageIcon);

        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
        tempPanel.add(tempLabel);
    }
    public void grayScale(){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        JLabel tempLabel = new JLabel();

        Image colorImage = new ImageIcon(String.valueOf(file)).getImage();
        ImageFilter filter = new GrayFilter(true, 50);
        ImageProducer producer = new FilteredImageSource(colorImage.getSource(), filter);
        Image image = Toolkit.getDefaultToolkit().createImage(producer);
        ImageIcon imageIcon = new ImageIcon(image);
        tempLabel.setIcon(imageIcon);

        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
        tempPanel.add(tempLabel);
    }
    public void showResizeImage(int w, int h){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        JLabel tempLabel = new JLabel();
        // catch for very big scales
        try {
            ImageIcon imageIcon = new ImageIcon
                    (new ImageIcon(String.valueOf(file)).getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
            tempLabel.setIcon(imageIcon);
        }
        catch (Exception e){
            System.out.println("Invalid scales!");
        }

        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
        tempPanel.add(tempLabel);
    }
    public void showBrightnessImage(float f) throws IOException {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        JLabel tempLabel = new JLabel();

        BufferedImage image = ImageIO.read(file);
        RescaleOp op = new RescaleOp(f, 0, null);
        Image brightImage = op.filter(image, image);
        ImageIcon imageIcon = new ImageIcon(brightImage);
        tempLabel.setIcon(imageIcon);

        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
        tempPanel.add(tempLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==resizeButton){
            // check if file is selected or not
            if(file == null){
                this.getContentPane().removeAll();
                JLabel jLabel = new JLabel("No file selected!");
                jLabel.setBounds(300,180,100,100);
                this.add(jLabel);
                this.mainPanel();
                this.revalidate();
                this.repaint();
            }
            else {
                // removes previous panel
                this.getContentPane().removeAll();
                this.resizePanel();
                this.revalidate();
                this.repaint();
            }

        }else if(e.getSource()== showImageButton){
            if(file == null){
                this.getContentPane().removeAll();
                JLabel jLabel = new JLabel("No file selected!");
                jLabel.setBounds(300,180,100,100);
                this.add(jLabel);
                this.mainPanel();
                this.revalidate();
                this.repaint();
            }
            else {
                this.showimage();
            }

        }else if(e.getSource()==grayscaleButton){
            if(file == null){
                this.getContentPane().removeAll();
                JLabel jLabel = new JLabel("No file selected!");
                jLabel.setBounds(300,180,100,100);
                this.add(jLabel);
                this.mainPanel();
                this.revalidate();
                this.repaint();
            }
            else {
                this.grayScale();
            }

        }else if(e.getSource()== showResizeButton){
            // check scales to be valid number
            try {
                w = Integer.parseInt(widthTextField.getText());
                h = Integer.parseInt(heightTextField.getText());
                showResizeImage(w, h);
            }
            catch (Exception ex){
                this.getContentPane().removeAll();
                JLabel jLabel = new JLabel("Invalid input!");
                jLabel.setBounds(320,170,100,100);
                this.add(jLabel);
                this.resizePanel();
                this.revalidate();
                this.repaint();
            }

        }else if(e.getSource()==brightnessButton){
            if(file == null){
                this.getContentPane().removeAll();
                JLabel jLabel = new JLabel("No file selected!");
                jLabel.setBounds(300,180,100,100);
                this.add(jLabel);
                this.mainPanel();
                this.revalidate();
                this.repaint();
            }
            else {
                this.getContentPane().removeAll();
                this.brightnessPanel();
                this.revalidate();
                this.repaint();
            }

        }else if(e.getSource()== showBrightnessButton){
            // check brightness to be valid number
            try {
                brightenFactor = Float.parseFloat(brightnessTextField.getText());
                showBrightnessImage(brightenFactor);
            } catch (Exception ex) {
                this.getContentPane().removeAll();
                JLabel jLabel = new JLabel("Invalid input!");
                jLabel.setBounds(320,170,100,100);
                this.add(jLabel);
                this.brightnessPanel();
                this.revalidate();
                this.repaint();
            }

        }else if(e.getSource()== selectFileButton){
            this.getContentPane().removeAll();
            this.chooseFileImage();
            this.revalidate();
            this.repaint();

        }else if(e.getSource()==closeButton){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }

        else if(e.getSource()==backButton){
            this.getContentPane().removeAll();
            this.mainPanel();
            this.revalidate();
            this.repaint();
        }
    }
}