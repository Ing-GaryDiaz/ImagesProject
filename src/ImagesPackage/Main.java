package ImagesPackage;

public class Main {
    public static void main(String[] args) {
        // Ruta de la imagen a procesar
        String imagePath = "./apple.jpg";

        // Convertir imagen a matriz de píxeles
        int[][] imageData = ImageProcessing.imgToTwoD(imagePath);


        // Crear un lienzo en blanco de 500x500
        int[][] blankCanvas = new int[500][500];

        int[][] canvas = new int[500][500];






        if (imageData != null) {
            //Aplicar trimmed a la imagen original

            int[][] trimmed = ImageProcessing.trimBorders(imageData, 360);

            //Guardar la imagen trimmed
            ImageProcessing.twoDToImage(trimmed, "./trimmed_apple.jpg");

            System.out.println("Procesamiento completado. Imagen guardada como 'trimmed_apple.jpg'.");
        } else {
            System.out.println("Error al cargar la imagen.");
        }


        if (imageData != null) {
            //Aplicar filtro de color negativo
            int[][] negativeImage = ImageProcessing.negativeColor(imageData);

            //Guardar la imagen negativa
            ImageProcessing.twoDToImage(negativeImage, "./negative_apple.jpg");

            System.out.println("Procesamiento completado. Imagen guardada como 'negative_apple.jpg'.");
        } else {
            System.out.println("Error al cargar la imagen.");
        }


        if (imageData != null) {
            // Aplicar the stretchHorizontally method
            int[][] stretchedImage = ImageProcessing.stretchHorizontally(imageData);
            ImageProcessing.twoDToImage(stretchedImage, "./stretched_apple.jpg");

            System.out.println("Procesamiento completado. Imagen guardada como 'stretched_apple.jpg'.");
        } else {
            System.out.println("Error al cargar la imagen.");
        }

        if (imageData != null) {
            // Aplicar the shrinkVertically method
            int[][] shrunkImage = ImageProcessing.shrinkVertically(imageData);
            ImageProcessing.twoDToImage(shrunkImage, "./shrunk_apple.jpg");

            System.out.println("Procesamiento completado. Imagen guardada como 'shrunk_apple.jpg'.");
        } else {
            System.out.println("Error al cargar la imagen.");
        }


        if (imageData != null) {
            // Aplicar the invertImage method
            int[][] invertedImage = ImageProcessing.invertImage(imageData);
            ImageProcessing.twoDToImage(invertedImage, "./inverted_apple.jpg");

            System.out.println("Procesamiento completado. Imagen guardada como 'inverted_apple.jpg'.");
        } else {
            System.out.println("Error al cargar la imagen.");
        }

        if (imageData != null) {
            // Aplicar the colorFilter method
            int[][] filteredImage = ImageProcessing.colorFilter(imageData, 50, -30, 20);
            ImageProcessing.twoDToImage(filteredImage, "./filtered_apple.jpg");

            System.out.println("Procesamiento completado. Imagen guardada como 'filtered_apple.jpg'.");
        } else {
            System.out.println("Error al cargar la imagen.");
        }
        if (blankCanvas != null) {
            // Aplicar el método paintRandomImage
            int[][] randomImage = ImageProcessing.paintRandomImage(blankCanvas);
            ImageProcessing.twoDToImage(randomImage, "./random_image.jpg");

            System.out.println("Procesamiento completado. Imagen guardada como 'random_image.jpg'.");
        } else {
            System.out.println("Error al crear imagen(lienzo).");
        }

        if (imageData != null) {
            // Crear un color Azul
            int[] rgba = {0, 0, 255, 255};
            int color = ImageProcessing.getColorIntValFromRGBA(rgba);

            // Aplicar el método paintRectangle
            int[][] rectangleImage = ImageProcessing.paintRectangle(imageData, 800, 200, 1000, 1500, color);
            ImageProcessing.twoDToImage(rectangleImage, "./rectangle_image.jpg");

            System.out.println("Procesamiento completado. Imagen guardada como 'rectangle_image.jpg'.");
        } else {
            System.out.println("Error al cargar la imagen.");
        }


        if (blankCanvas != null) {
            // Aplicar el método generateRectangles
            canvas = ImageProcessing.generateRectangles(canvas, 1000);
            ImageProcessing.twoDToImage(canvas, "abstract_art.jpg");
            ImageProcessing.twoDToImage(ImageProcessing.generateRectangles(new int[500][500], 1000), "abstract_art.jpg");

            System.out.println("Procesamiento completado. Imagen guardada como 'abstract_art.jpg'.");
        } else {
            System.out.println("Error al crear imagen(lienzo).");
        }


    }
}













