package ClubSocial.Interfaz;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import ClubSocial.Metodos.Socio;
import ClubSocial.Metodos.Socio.Tipo;

public class ISocio extends JPanel implements ActionListener{
    private final static String REGISTRAR_CONSUMO = "Registrar consumo";
    private final static String AUMENTAR_FONDOS = "Aumentar fondos";
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtFondos;
    private JTextField txtTipoSuscripcion;
    private JButton btnRegistrarConsumo;
    private JButton btnAumentarFondos;
    private InterfazClub principal;

    public ISocio(InterfazClub pPrincipal )
    {
        setBorder( new TitledBorder( "Datos socio" ) );
        setLayout( new BorderLayout( ) );
        principal = pPrincipal;

        JPanel panelDatos = new JPanel( );
        panelDatos.setLayout( new GridLayout( 6, 2, 5, 5 ) );
        add( panelDatos, BorderLayout.CENTER );

        JLabel lblCedula = new JLabel( "Cédula:" );
        panelDatos.add( lblCedula );

        txtCedula = new JTextField( );
        txtCedula.setEditable( false );
        panelDatos.add( txtCedula );

        JLabel lblNombre = new JLabel( "Nombre:" );
        panelDatos.add( lblNombre );

        txtNombre = new JTextField( );
        txtNombre.setEditable( false );
        panelDatos.add( txtNombre );

        JLabel lblFondos = new JLabel( "Fondos disponibles:" );
        panelDatos.add( lblFondos );

        txtFondos = new JTextField( );
        txtFondos.setEditable( false );
        panelDatos.add( txtFondos );

        JLabel lblTipoSuscripcion = new JLabel( "Tipo suscripción:" );
        panelDatos.add( lblTipoSuscripcion );
        txtTipoSuscripcion = new JTextField( );
        txtTipoSuscripcion.setEditable( false );
        panelDatos.add( txtTipoSuscripcion );

        panelDatos.add( new JLabel( ) );

        btnRegistrarConsumo = new JButton( REGISTRAR_CONSUMO );
        btnRegistrarConsumo.setEnabled( false );
        btnRegistrarConsumo.setActionCommand( REGISTRAR_CONSUMO );
        btnRegistrarConsumo.addActionListener( this );
        panelDatos.add( btnRegistrarConsumo );

        panelDatos.add( new JLabel( ) );

        btnAumentarFondos = new JButton( AUMENTAR_FONDOS );
        btnAumentarFondos.setEnabled( false );
        btnAumentarFondos.setActionCommand( AUMENTAR_FONDOS );
        btnAumentarFondos.addActionListener( this );
        panelDatos.add( btnAumentarFondos );
    }

    public void actualizar( Socio pSocio )
    {
        txtCedula.setText( pSocio.darCedula( ) );
        txtNombre.setText( pSocio.darNombre( ) );
        DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
        df.applyPattern( "$###,###.##" );
        txtFondos.setText( df.format( pSocio.darFondos( ) ) );
        Tipo tipoSuscripcion = pSocio.darTipo( );
        switch( tipoSuscripcion )
        {
            case REGULAR:
                txtTipoSuscripcion.setText( "Regular" );
                break;
            case VIP:
                txtTipoSuscripcion.setText( "VIP" );
                break;
        }
        btnAumentarFondos.setEnabled( true );
        btnRegistrarConsumo.setEnabled( true );
    }

    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );

        if( comando.equals( REGISTRAR_CONSUMO ) )
        {
            RegistrarConsumo dialogo = new RegistrarConsumo( principal, principal.darAutorizadosSocio( ) );
            dialogo.setVisible( true );
        }
        else if( comando.equals( AUMENTAR_FONDOS ) )
        {
            principal.aumentarFondos( );
        }

    }
}
