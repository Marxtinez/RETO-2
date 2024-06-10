package Controller;

import Model.Empresa;
import Model.EmpresaTutorTrabajador;
import Model.Trabajador;
import Model.TutorFCT;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//Consulta C1
public class ControladorConsultaEmpresaPorCIF {
    static ArrayList<EmpresaTutorTrabajador> resultados;

    public static void cargaConsultaEmpresa(String CIF) throws SQLException {
        resultados.clear();

        String consultaSQL = "SELECT \n" +
                "    e.cif AS CIF,\n" +
                "    e.nombre AS Nombre_Empresa,\n" +
                "    e.direccion AS Direccion_Empresa,\n" +
                "    e.tecnologias AS Tecnologias_Empresa,\n" +
                "    e.sector AS Sector_Empresa,\n" +
                "    e.telefono AS Telefono_Empresa,\n" +
                "    e.num_empleados AS Numero_Empleados,\n" +
                "    e.ult_anio_colab AS Ultimo_Anio_Colaboracion,\n" +
                "    tf.id_tutor AS ID_Profesor,\n" +
                "    tf.nombre AS Nombre_Profesor,\n" +
                "    tf.email AS Email_Profesor,\n" +
                "    tf.telefono AS Telefono_Profesor,\n" +
                "    t.id_trabajador AS ID_Trabajador,\n" +
                "    t.nombre AS Nombre_Trabajador,\n" +
                "    t.telefono AS Telefono_Trabajador,\n" +
                "    t.cargo AS Cargo_Trabajador,\n" +
                "    t.persona_contacto AS Persona_Contacto\n" +
                "FROM \n" +
                "    empresa e\n" +
                "JOIN \n" +
                "    grupo_realiza_empresa gre ON e.cif = gre.cif\n" +
                "JOIN \n" +
                "    tutor_responsable_grupo trg ON gre.id_grupo = trg.id_grupo AND gre.curso = trg.curso\n" +
                "JOIN \n" +
                "    tutorfct tf ON trg.id_tutor = tf.id_tutor\n" +
                "LEFT JOIN \n" +
                "    trabajador t ON e.cif = t.cif\n" +
                "WHERE \n" +
                "    e.cif = ?;";
        PreparedStatement ps = ControladorConexion.miConexion.prepareStatement(consultaSQL);

            ps.setString(1, CIF);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Empresa empresa = new Empresa();
                    empresa.setCIF(rs.getString("CIF"));
                    empresa.setNombre(rs.getString("Nombre_Empresa"));
                    empresa.setDireccion(rs.getString("Direccion_Empresa"));
                    empresa.setTecnologias(rs.getString("Tecnologias_Empresa"));
                    empresa.setSector(rs.getString("Sector_Empresa"));
                    empresa.setTelefono(rs.getString("Telefono_Empresa"));
                    empresa.setNum_empleados(rs.getInt("Numero_Empleados"));
                    empresa.setUlt_anio_colab(rs.getInt("Ultimo_Anio_Colaboracion"));

                    TutorFCT tutor = new TutorFCT();
                    tutor.setId_tutor(rs.getInt("ID_Profesor"));
                    tutor.setNombre(rs.getString("Nombre_Profesor"));
                    tutor.setEmail(rs.getString("Email_Profesor"));
                    tutor.setTelefono(rs.getString("Telefono_Profesor"));

                    Trabajador trabajador = new Trabajador();
                    trabajador.setId_trabajador(rs.getInt("ID_Trabajador"));
                    trabajador.setNombre(rs.getString("Nombre_Trabajador"));
                    trabajador.setTelefono(rs.getString("Telefono_Trabajador"));
                    trabajador.setCargo(rs.getString("Cargo_Trabajador"));
                    trabajador.setPersona_contacto(rs.getBoolean("Persona_Contacto"));

                    EmpresaTutorTrabajador resultado = new EmpresaTutorTrabajador(empresa, tutor, trabajador);
                    resultados.add(resultado);
                }
            }
    }

    public static void main(String[] args) {
        try {
            cargaConsultaEmpresa("CIF001");

            for (EmpresaTutorTrabajador resultado : resultados) {
                System.out.println(resultado.getEmpresa().toString());
                System.out.println(resultado.getTrabajador().toString());
                System.out.println(resultado.getTutorFCT().toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
