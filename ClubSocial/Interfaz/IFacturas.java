package ClubSocial.Interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import ClubSocial.Metodos.Factura;
@SuppressWarnings("serial")
public class IFacturas extends JPanel implements ActionListener
{
    private static final String PAGAR_FACTURA = "Pagar factura";
    private JScrollPane scrollDesplazamiento;
    private JList listaFacturas;
    private JButton btnPagarFactura;
    private InterfazClub principal;
    public IFacturas(InterfazClub pPrincipal )
    {
        principal = pPrincipal;
        setLayout( new BorderLayout( ) );
        setBorder( new TitledBorder( "Facturas" ) );

        scrollDesplazamiento = new JScrollPane( );

        listaFacturas = new JList( );
        listaFacturas.setSelectionMode( javax.swing.ListSelectionModel.SINGLE_SELECTION );

        scrollDesplazamiento.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scrollDesplazamiento.setViewportView( listaFacturas );
        add( scrollDesplazamiento, BorderLayout.CENTER );

        btnPagarFactura = new JButton( PAGAR_FACTURA);
        btnPagarFactura.setActionCommand( PAGAR_FACTURA );
        btnPagarFactura.addActionListener( this );
        btnPagarFactura.setEnabled( false );
        add( btnPagarFactura, BorderLayout.SOUTH );
    }
    public void actionPerformed( ActionEvent pEvento )
    {
        String actionCommand = pEvento.getActionCommand( );

        if( PAGAR_FACTURA.equals( actionCommand ) )
        {
            principal.pagarFactura( );
        }
    }
    public boolean hayFacturaSeleccionada( )
    {
        return listaFacturas.getSelectedIndex( ) != -1;
    }
    public int darPosicionFacturaSeleccionada( )
    {
        return listaFacturas.getSelectedIndex( );
    }
    public void cambiarFacturas( ArrayList<Factura> pFacturas )
    {
        listaFacturas.removeAll( );
        listaFacturas.setListData( pFacturas.toArray( ) );
        if(!pFacturas.isEmpty( ))
        {
            listaFacturas.setSelectedIndex( 0 );
           btnPagarFactura.setEnabled( true );
        }
        else
        {
            btnPagarFactura.setEnabled( false );
        }
    }

}
