package ClubSocial.Interfaz;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import ClubSocial.Metodos.Socio;

@SuppressWarnings("serial")
public class ListaSocios extends JPanel implements ListSelectionListener, ActionListener {

    private final static String AFILIAR = "Afiliar socio";
    private JScrollPane scrollSocios;
    private JList socios;
    private JButton btnAfiliar;
    private InterfazClub principal;
    public ListaSocios(InterfazClub pPrincipal )
    {
        principal = pPrincipal;
        setLayout( new BorderLayout( ) );
        setBorder( new TitledBorder( "Socios" ) );
        setPreferredSize( new Dimension( 300, 0 ) );

        socios = new JList( );

        scrollSocios = new JScrollPane( socios );
        socios.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        socios.addListSelectionListener( this );
        scrollSocios.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scrollSocios.setHorizontalScrollBarPolicy( javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        add( scrollSocios, BorderLayout.CENTER );

        btnAfiliar = new JButton( AFILIAR );
        btnAfiliar.setActionCommand( AFILIAR );
        btnAfiliar.addActionListener( this );
        add( btnAfiliar, BorderLayout.SOUTH );
    }
    public String darCedulaSocioSeleccionado( )
    {
        String cadena = "";
        if( socios.getSelectedValue( ) != null )
        {
            Socio actual = ( Socio )socios.getSelectedValue( );
            cadena = actual.darCedula( );
        }
        return cadena;
    }
    public void refrescar( ArrayList<Socio> pLista )
    {
        socios.setListData( pLista.toArray( ) );
        if(!pLista.isEmpty( ))
        {
            socios.setSelectedIndex( 0 );
        }
    }
    public void cambiarActual( Socio pSocio)
    {
        socios.setSelectedValue( pSocio, true );
    }
    public void valueChanged( ListSelectionEvent pEvento )
    {
        if( socios.getSelectedValue( ) != null )
        {
            principal.actualizar();
        }
    }
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if( comando.equals( AFILIAR ) )
        {
            AfiliarSocio dialogo = new AfiliarSocio( principal );
            dialogo.setVisible( true );
        }
    }
}
