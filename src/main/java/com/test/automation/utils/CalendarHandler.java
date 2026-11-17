package com.test.automation.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.Duration;
import java.util.List;

public class CalendarHandler {
    private static final Logger logger = LogManager.getLogger(CalendarHandler.class);
    private WebDriver driver;
    private WebDriverWait wait;
    private static final int TIMEOUT = 10;

    public CalendarHandler(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    /**
     * Obtiene el último elemento disponible del calendario
     * @param calendarLocator Localizador del contenedor del calendario
     * @param dayItemLocator Localizador de los elementos de día dentro del calendario
     * @return WebElement del último día disponible
     */
    public WebElement getLastCalendarDay(By calendarLocator, By dayItemLocator) {
        try {
            WebElement calendar = wait.until(ExpectedConditions.presenceOfElementLocated(calendarLocator));
            logger.info("Calendario encontrado");

            List<WebElement> days = calendar.findElements(dayItemLocator);
            if (days.isEmpty()) {
                logger.error("No se encontraron elementos de día en el calendario");
                return null;
            }

            WebElement lastDay = days.get(days.size() - 1);
            logger.info("Último día del calendario capturado. Total de días: " + days.size());
            return lastDay;
        } catch (Exception e) {
            logger.error("Error al obtener el último elemento del calendario: " + e.getMessage());
            return null;
        }
    }

    /**
     * Obtiene el último elemento disponible del calendario y hace clic en él
     * @param calendarLocator Localizador del contenedor del calendario
     * @param dayItemLocator Localizador de los elementos de día dentro del calendario
     * @return true si el clic fue exitoso, false en caso contrario
     */
    public boolean clickLastCalendarDay(By calendarLocator, By dayItemLocator) {
        try {
            WebElement lastDay = getLastCalendarDay(calendarLocator, dayItemLocator);
            if (lastDay != null) {
                wait.until(ExpectedConditions.elementToBeClickable(lastDay));
                lastDay.click();
                logger.info("Clic exitoso en el último día del calendario");
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("Error al hacer clic en el último día del calendario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene el texto del último elemento del calendario
     * @param calendarLocator Localizador del contenedor del calendario
     * @param dayItemLocator Localizador de los elementos de día dentro del calendario
     * @return Texto del último día
     */
    public String getLastCalendarDayText(By calendarLocator, By dayItemLocator) {
        try {
            WebElement lastDay = getLastCalendarDay(calendarLocator, dayItemLocator);
            if (lastDay != null) {
                String dayText = lastDay.getText().trim();
                logger.info("Texto del último día: " + dayText);
                return dayText;
            }
            return null;
        } catch (Exception e) {
            logger.error("Error al obtener el texto del último día del calendario: " + e.getMessage());
            return null;
        }
    }

    /**
     * Obtiene todos los elementos del calendario
     * @param calendarLocator Localizador del contenedor del calendario
     * @param dayItemLocator Localizador de los elementos de día dentro del calendario
     * @return Lista de WebElements del calendario
     */
    public List<WebElement> getAllCalendarDays(By calendarLocator, By dayItemLocator) {
        try {
            WebElement calendar = wait.until(ExpectedConditions.presenceOfElementLocated(calendarLocator));
            List<WebElement> days = calendar.findElements(dayItemLocator);
            logger.info("Total de días en el calendario: " + days.size());
            return days;
        } catch (Exception e) {
            logger.error("Error al obtener los elementos del calendario: " + e.getMessage());
            return null;
        }
    }

    /**
     * Obtiene el penúltimo elemento del calendario
     * @param calendarLocator Localizador del contenedor del calendario
     * @param dayItemLocator Localizador de los elementos de día dentro del calendario
     * @return WebElement del penúltimo día disponible
     */
    public WebElement getSecondLastCalendarDay(By calendarLocator, By dayItemLocator) {
        try {
            List<WebElement> days = getAllCalendarDays(calendarLocator, dayItemLocator);
            if (days != null && days.size() >= 2) {
                WebElement secondLastDay = days.get(days.size() - 2);
                logger.info("Penúltimo día del calendario capturado");
                return secondLastDay;
            }
            logger.warn("No hay suficientes elementos en el calendario para obtener el penúltimo");
            return null;
        } catch (Exception e) {
            logger.error("Error al obtener el penúltimo día del calendario: " + e.getMessage());
            return null;
        }
    }

    /**
     * Obtiene un elemento del calendario por su posición
     * @param calendarLocator Localizador del contenedor del calendario
     * @param dayItemLocator Localizador de los elementos de día dentro del calendario
     * @param index Índice del elemento (0-based)
     * @return WebElement en la posición especificada
     */
    public WebElement getCalendarDayByIndex(By calendarLocator, By dayItemLocator, int index) {
        try {
            List<WebElement> days = getAllCalendarDays(calendarLocator, dayItemLocator);
            if (days != null && index >= 0 && index < days.size()) {
                WebElement day = days.get(index);
                logger.info("Día del calendario en índice " + index + " capturado");
                return day;
            }
            logger.warn("Índice fuera de rango. Total de días: " + (days != null ? days.size() : 0));
            return null;
        } catch (Exception e) {
            logger.error("Error al obtener el día por índice: " + e.getMessage());
            return null;
        }
    }
}
