package ClubSocial.Interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import ClubSocial.Metodos.Socio.Tipo;

public class AfiliarSocio extends JDialog implements ActionListener{
    private static final String TIPO_REGULAR = "Regular";
    private static final String TIPO_VIP = "VIP";
    private static final String AFILIAR = "AFILIAR";
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JComboBox<String> cmbTipoSuscripcion;
    private JButton botonAfiliar;
    private InterfazClub principal;
    public AfiliarSocio(InterfazClub pPrincipal )
    {
        principal = pPrincipal;
        setTitle( "Afiliar nuevo socio" );
        setSize( 350, 250 );
        setLocationRelativeTo( principal );

        JPanel panelGeneral = new JPanel( );
        panelGeneral.setBorder( new CompoundBorder( new TitledBorder( "Datos socio" ), new EmptyBorder( 3, 3, 3, 3 ) ) );
        panelGeneral.setLayout( new BorderLayout( ) );
        add( panelGeneral );

        JPanel panelDatos = new JPanel( );
        panelDatos.setLayout( new GridLayout( 4, 2, 3, 3 ) );
        panelGeneral.add( panelDatos, BorderLayout.CENTER );

        JLabel etiquetaNombre = new JLabel( "Nombre completo:" );
        panelDatos.add( etiquetaNombre );

        txtNombre = new JTextField( );
        panelDatos.add( txtNombre );

        JLabel etiquetaCedula = new JLabel( "Cedula:" );
        panelDatos.add( etiquetaCedula );

        txtCedula = new JTextField( );
        panelDatos.add( txtCedula );

        JLabel etiquetaTipo = new JLabel( "Tipo suscripcion: " );
        panelDatos.add( etiquetaTipo );

        cmbTipoSuscripcion = new JComboBox<String>( );
        cmbTipoSuscripcion.addItem( TIPO_REGULAR );
        cmbTipoSuscripcion.addItem( TIPO_VIP );
        panelDatos.add( cmbTipoSuscripcion );

        panelDatos.add( new JLabel( ) );
        panelDatos.add( new JLabel( ) );

        botonAfiliar = new JButton( "Afiliar" );
        botonAfiliar.setActionCommand( AFILIAR );
        botonAfiliar.addActionListener( this );
        panelGeneral.add( botonAfiliar, BorderLayout.SOUTH );
    }
    public void actionPerformed( ActionEvent pEvento )
    {
        String actionCommand = pEvento.getActionCommand( );

        if( AFILIAR.equals( actionCommand ) )
        {
            String strNombre = txtNombre.getText( );
            String strCedula = txtCedula.getText( );
            String strTipoSuscripcion = ( String )cmbTipoSuscripcion.getSelectedItem( );
            Tipo tipoSuscripcion = null;
            switch( ( strTipoSuscripcion ) )
            {
                case ( TIPO_REGULAR ):
                    tipoSuscripcion = Tipo.REGULAR;
                    break;
                case ( TIPO_VIP ):
                    tipoSuscripcion = Tipo.VIP;
                    break;
            }
            if( strNombre == null || strNombre.isEmpty( ) || strCedula == null || strCedula.isEmpty( ) )
            {
                JOptionPane.showMessageDialog( this, "Debe ingresar todos los datos.", "Afiliar socio", JOptionPane.ERROR_MESSAGE );
            }
            else
            {
                principal.afiliarSocio( strCedula, strNombre, tipoSuscripcion );
                dispose( );
            }
        }

    }

}
