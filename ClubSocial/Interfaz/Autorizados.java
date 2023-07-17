package ClubSocial.Interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class Autorizados extends JPanel implements ActionListener
{
    private final static String AGREGAR_AUTORIZADO = "Agregar autorizado";
    private JScrollPane scrollAutorizados;
    private JList listaAutorizados;
    private JButton btnAgregarAutorizado;
    private InterfazClub principal;
    public Autorizados(InterfazClub pPrincipal )
    {
        principal = pPrincipal;

        setLayout( new BorderLayout( ) );
        setBorder( new TitledBorder( "Autorizados" ) );

        listaAutorizados = new JList( new DefaultListModel( ) );
        listaAutorizados.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );

        scrollAutorizados = new JScrollPane( listaAutorizados );
        scrollAutorizados.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scrollAutorizados.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );

        
        JPanel contenedor = new JPanel( );
        contenedor.setLayout( new BorderLayout( ) );
        contenedor.add( scrollAutorizados, BorderLayout.CENTER );
        add( contenedor, BorderLayout.CENTER );

        btnAgregarAutorizado = new JButton( AGREGAR_AUTORIZADO );
        btnAgregarAutorizado.setActionCommand( AGREGAR_AUTORIZADO );
        btnAgregarAutorizado.addActionListener( this );
        btnAgregarAutorizado.setEnabled( false );
        add( btnAgregarAutorizado, BorderLayout.SOUTH );

    }
    public void cambiarAutorizados( ArrayList pAutorizados )
    {
        listaAutorizados.removeAll( );
        listaAutorizados.setListData( pAutorizados.toArray( ) );
        btnAgregarAutorizado.setEnabled( true );
       
    }
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if( comando.equals( AGREGAR_AUTORIZADO ) )
        {
            principal.agregarAutorizado( );
        }

    }
}
