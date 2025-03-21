package ImagesPackage;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import javax.imageio.ImageIO;
import java.util.Random;

public class ImageProcessing {

    // Método para recortar bordes
    public static int[][] trimBorders(int[][] imageTwoD, int pixelCount) {
        if (imageTwoD.length > pixelCount * 2 && imageTwoD[0].length > pixelCount * 2) {
            int[][] trimmedImg = new int[imageTwoD.length - pixelCount * 2][imageTwoD[0].length - pixelCount * 2];
            for (int i = 0; i < trimmedImg.length; i++) {
                for (int j = 0; j < trimmedImg[i].length; j++) {
                    trimmedImg[i][j] = imageTwoD[i + pixelCount][j + pixelCount];
                }
            }
            return trimmedImg;
        } else {
            System.out.println("Cannot trim that many pixels from the given image.");
            return imageTwoD;
        }
    }

    //Método para imagen negativa:

    public static int[][] negativeColor(int[][] imageTwoD) {
        // Crear una nueva matriz para la imagen negativa
        int[][] negativeImage = new int[imageTwoD.length][imageTwoD[0].length];

        for (int i = 0; i < imageTwoD.length; i++) {
            for (int j = 0; j < imageTwoD[i].length; j++) {
                // Obtener componentes RGBA del píxel actual
                int[] rgba = getRGBAFromPixel(imageTwoD[i][j]);

                // Invertir los componentes de color RGB
                rgba[0] = 255 - rgba[0]; // Rojo
                rgba[1] = 255 - rgba[1]; // Verde
                rgba[2] = 255 - rgba[2]; // Azul
                // El canal alfa (transparencia) queda igual

                // Reconstruir el píxel con colores negativos
                negativeImage[i][j] = getColorIntValFromRGBA(rgba);
            }
        }

        return negativeImage;
    }

    //Método para imagen stretchHorizontal:
    public static int[][] stretchHorizontally(int[][] imageTwoD) {
        int originalRows = imageTwoD.length;
        int originalCols = imageTwoD[0].length;
        int[][] stretchedImg = new int[originalRows][originalCols * 2];

        for (int i = 0; i < originalRows; i++) {
            for (int j = 0; j < originalCols; j++) {
                stretchedImg[i][j * 2] = imageTwoD[i][j];
                stretchedImg[i][j * 2 + 1] = imageTwoD[i][j];
            }
        }

        return stretchedImg;
    }
    //Método para imagen stretchVertical:
    public static int[][] shrinkVertically(int[][] imageTwoD) {
        int originalRows = imageTwoD.length;
        int originalCols = imageTwoD[0].length;
        int[][] shrunkImg = new int[originalRows][originalCols / 2];

        for (int i = 0; i < originalRows; i++) {
            for (int j = 0; j < originalCols / 2; j++) {
                shrunkImg[i][j] = imageTwoD[i][j * 2];
            }
        }

        return shrunkImg;
    }
    //Método para imagen invertida:
    public static int[][] invertImage(int[][] imageTwoD) {
        int originalRows = imageTwoD.length;
        int originalCols = imageTwoD[0].length;
        int[][] invertedImg = new int[originalRows][originalCols];

        for (int i = 0; i < originalRows; i++) {
            for (int j = 0; j < originalCols; j++) {
                invertedImg[i][j] = imageTwoD[originalRows - 1 - i][originalCols - 1 - j];
            }
        }

        return invertedImg;
    }
    //Método para imagen con filtro de color:
    public static int[][] colorFilter(int[][] imageTwoD, int redChangeValue, int greenChangeValue, int blueChangeValue) {
        int[][] filteredImg = new int[imageTwoD.length][imageTwoD[0].length];

        for (int i = 0; i < imageTwoD.length; i++) {
            for (int j = 0; j < imageTwoD[i].length; j++) {
                int[] rgba = getRGBAFromPixel(imageTwoD[i][j]);
                rgba[0] = Math.min(255, Math.max(0, rgba[0] + redChangeValue)); // Red
                rgba[1] = Math.min(255, Math.max(0, rgba[1] + greenChangeValue)); // Green
                rgba[2] = Math.min(255, Math.max(0, rgba[2] + blueChangeValue)); // Blue
                filteredImg[i][j] = getColorIntValFromRGBA(rgba);
            }

        }
        return filteredImg;
    }
    //Método para imagen random:
    public static int[][] paintRandomImage(int[][] imageTwoD) {
        Random rand = new Random();
        for (int i = 0; i < imageTwoD.length; i++) {
            for (int j = 0; j < imageTwoD[i].length; j++) {
                int red = rand.nextInt(256);
                int green = rand.nextInt(256);
                int blue = rand.nextInt(256);
                int[] rgba = { red, green, blue, 255 }; // 255 es el valor alfa
                imageTwoD[i][j] = getColorIntValFromRGBA(rgba);
            }
        }
        return imageTwoD;
    }


    //Método para imagen de rectangulo sobre lienzo:
    public static int[][] paintRectangle(int[][] imageTwoD, int width, int height, int rowPosition, int colPosition, int color) {
        for (int i = 0; i < imageTwoD.length; i++) {
            for (int j = 0; j < imageTwoD[i].length; j++) {
                if (i >= rowPosition && i < rowPosition + height && j >= colPosition && j < colPosition + width) {
                    imageTwoD[i][j] = color;
                }
            }
        }
        return imageTwoD;
    }
    //Método para generacion de imagenes rectangl :
    public static int[][] generateRectangles(int[][] canvas, int numRectangles){
        Random rand = new Random();


        for (int i = 0; i < numRectangles; i++) {
            // Generar ancho y alto aleatorios
            int height = rand.nextInt(canvas.length/4) +1;
            int width = rand.nextInt(canvas[0].length/4) + 1;


            // Generar posición fila y columna aleatorias
            int row = rand.nextInt(canvas.length - height);
            int col = rand.nextInt(canvas[0].length - width);

            // Generar color aleatorio
            int r = rand.nextInt(256); // Valor para Rojo (0 a 255)
            int g = rand.nextInt(256); // Valor para Verde (0 a 255)
            int b = rand.nextInt(256); // Valor para Azul (0 a 255)
            int a = 255; // Opacidad
            int[] rgba = {r,g,b,a};
            int color = getColorIntValFromRGBA(rgba);

            // Pintar rectángulo
            paintRectangle(canvas, row, col, width, height, color);
        }

        return canvas;
    }








    public static void viewImageData ( int[][] imageTwoD){
            if (imageTwoD.length > 3 && imageTwoD[0].length > 3) {
                int[][] rawPixels = new int[3][3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        rawPixels[i][j] = imageTwoD[i][j];
                    }
                }
                System.out.println("Raw pixel data from the top left corner.");
                System.out.print(Arrays.deepToString(rawPixels).replace("],", "],\n") + "\n");
                int[][][] rgbPixels = new int[3][3][4];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        rgbPixels[i][j] = getRGBAFromPixel(imageTwoD[i][j]);
                    }
                }
                System.out.println();
                System.out.println("Extracted RGBA pixel data from top the left corner.");
                for (int[][] row : rgbPixels) {
                    System.out.print(Arrays.deepToString(row) + System.lineSeparator());
                }
            } else {
                System.out.println("The image is not large enough to extract 9 pixels from the top left corner");
            }
        }


        public static int[] getRGBAFromPixel ( int pixelColorValue){
            Color pixelColor = new Color(pixelColorValue);
            return new int[]{pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue(), pixelColor.getAlpha()};
        }

        public static int getColorIntValFromRGBA ( int[] colorData){
            if (colorData.length == 4) {
                Color color = new Color(colorData[0], colorData[1], colorData[2], colorData[3]);
                return color.getRGB();
            } else {
                System.out.println("Incorrect number of elements in RGBA array.");
                return -1;
            }
        }


        // Otros métodos como colorFilter, shrinkVertically, etc., pueden ir aquí

        public static int[][] imgToTwoD (String inputFileOrLink){
            try {
                BufferedImage image = null;
                if (inputFileOrLink.startsWith("http")) {
                    URL imageUrl = new URL(inputFileOrLink);
                    image = ImageIO.read(imageUrl);
                    if (image == null) {
                        System.out.println("Failed to get image from provided URL.");
                    }
                } else {
                    image = ImageIO.read(new File(inputFileOrLink));
                }
                int imgRows = image.getHeight();
                int imgCols = image.getWidth();
                int[][] pixelData = new int[imgRows][imgCols];
                for (int i = 0; i < imgRows; i++) {
                    for (int j = 0; j < imgCols; j++) {
                        pixelData[i][j] = image.getRGB(j, i);
                    }
                }
                return pixelData;
            } catch (Exception e) {
                System.out.println("Failed to load image: " + e.getLocalizedMessage());
                return null;
            }
        }

        public static void twoDToImage ( int[][] imgData, String fileName){
            try {
                int imgRows = imgData.length;
                int imgCols = imgData[0].length;
                BufferedImage result = new BufferedImage(imgCols, imgRows, BufferedImage.TYPE_INT_RGB);
                for (int i = 0; i < imgRows; i++) {
                    for (int j = 0; j < imgCols; j++) {
                        result.setRGB(j, i, imgData[i][j]);
                    }
                }
                File output = new File(fileName);
                ImageIO.write(result, "jpg", output);
            } catch (Exception e) {
                System.out.println("Failed to save image: " + e.getLocalizedMessage());
            }
        }
}



