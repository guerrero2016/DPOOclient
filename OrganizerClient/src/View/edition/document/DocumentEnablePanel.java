package View.edition.document;

/**
 * Representacio d'una classe que permeti habilitar o deshabilitar quelcom a partir dels canvis en un Document
 */
public interface DocumentEnablePanel {
    /**
     * Metode encarregat d'habilitar o deshabilitar quelcom
     * @param enableState Estat
     */
    void setDocumentEnableState(boolean enableState);
}