package ClubSocial.Interfaz;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class Opciones extends JPanel implements ActionListener{
    private final String OPCION_1 = "opcion 1";
    private final String OPCION_2 = "opcion 2";
    private InterfazClub principal;
    private JButton botonOpcion1;
    private JButton botonOpcion2;
    public Opciones(InterfazClub pPrincipal )
    {
        principal = pPrincipal;
        setLayout( new GridLayout( 1, 2, 3, 10 ) );
        setBorder( new TitledBorder( "Opciones" ) );

        botonOpcion1 = new JButton( "Opcion 1" );
        botonOpcion1.setActionCommand( OPCION_1 );
        botonOpcion1.addActionListener( this );
        add( botonOpcion1 );

        botonOpcion2 = new JButton( "Opcion 2" );
        botonOpcion2.setActionCommand( OPCION_2 );
        botonOpcion2.addActionListener( this );
        add( botonOpcion2 );

    }
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if( OPCION_1.equals( comando ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( OPCION_2.equals( comando ) )
        {
            principal.reqFuncOpcion2( );
        }
    }
}