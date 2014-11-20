package sample.modelo;

import java.io.Serializable;

/**
 * Created by juliosossai on 20/11/2014.
 */
public class Cliente implements Serializable {

    private Integer idCli;
    private String nomeCli;
    private String cnpjCli;
    private String emailCli;

    public Integer getIdCli() {
        return idCli;
    }

    public void setIdCli(Integer idCli) {
        this.idCli = idCli;
    }

    public String getNomeCli() {
        return nomeCli;
    }

    public void setNomeCli(String nomeCli) {
        this.nomeCli = nomeCli;
    }

    public String getCnpjCli() {
        return cnpjCli;
    }

    public void setCnpjCli(String cnpjCli) {
        this.cnpjCli = cnpjCli;
    }

    public String getEmailCli() {
        return emailCli;
    }

    public void setEmailCli(String emailCli) {
        this.emailCli = emailCli;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        if (!cnpjCli.equals(cliente.cnpjCli)) return false;
        if (!emailCli.equals(cliente.emailCli)) return false;
        if (!idCli.equals(cliente.idCli)) return false;
        if (!nomeCli.equals(cliente.nomeCli)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCli.hashCode();
        result = 31 * result + nomeCli.hashCode();
        result = 31 * result + cnpjCli.hashCode();
        result = 31 * result + emailCli.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCli=" + idCli +
                ", nomeCli='" + nomeCli + '\'' +
                ", cnpjCli='" + cnpjCli + '\'' +
                ", emailCli='" + emailCli + '\'' +
                '}';
    }

    public Cliente(Integer idCli, String nomeCli, String cnpjCli, String emailCli){

        this.idCli = idCli;
        this.nomeCli = nomeCli;
        this.cnpjCli = cnpjCli;
        this.emailCli = emailCli;

    }

    public Cliente() {

    }
}
