package com.rentadeherramientas.rentadeherramientas.infrastructure.reports;

public interface Font {
    /**
     * Gets the font name
     * @return String representing the font name
     */
    String getFontName();
    
    /**
     * Gets the font size
     * @return integer representing the font size in points
     */
    int getFontSize();
    
    /**
     * Checks if the font is bold
     * @return boolean indicating if the font is bold
     */
    boolean isBold();
    
    /**
     * Checks if the font is italic
     * @return boolean indicating if the font is italic
     */
    boolean isItalic();
}
