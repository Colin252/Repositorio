
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public static double getExchangeRate(String baseCurrency, String targetCurrency) {
        try {
            URL url = new URL(API_URL + baseCurrency);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject json = new JSONObject(response.toString());

            // Imprimir la respuesta para verificar su contenido
            System.out.println("Respuesta de la API: " + json.toString());

            // Verificar si la respuesta contiene "rates"
            if (!json.has("rates")) {
                System.out.println("Error: La API no devolvió tasas de cambio.");
                return -1;
            }

            JSONObject rates = (JSONObject) json.getJSONObject("rates");

            // Verificar si la moneda objetivo existe
            if (!rates.has(targetCurrency)) {
                System.out.println("Error: La moneda " + targetCurrency + " no está disponible.");
                return -1;
            }

            return rates.optDouble(targetCurrency, -1);

        } catch (Exception e) {
            System.out.println("Error al obtener los datos de la API: " + e.getMessage());
            return -1;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la moneda base (ej. USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Ingrese la moneda objetivo (ej. EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Ingrese la cantidad a convertir: ");
        double amount = scanner.nextDouble();

        double rate = getExchangeRate(baseCurrency, targetCurrency);
        if (rate != -1) {
            double convertedAmount = amount * rate;
            System.out.println("Cantidad convertida: " + convertedAmount + " " + targetCurrency);
        }

        scanner.close();
    }
}