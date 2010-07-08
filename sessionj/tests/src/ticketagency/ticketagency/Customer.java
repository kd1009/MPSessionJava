package ticketagency;

import java.net.*;
import java.util.*;
import sessionj.runtime.*;
import sessionj.runtime.net.*;
import sessionj.runtime.transport.*;

class Customer {
    private String TRAVEL_METHOD = "Paris by Eurostar";
    private Double MAX_PRICE;
    private Address ADDRESS = new Address("Customer\'s address.");
    final SJProtocol p_ca =
      new SJProtocol(
      ("H4sIAAAAAAAAAIVUX0hTURg/25xOE1umYmiauZlmbPRgRAY550TnFcemBELp" +
       "2b2n7br7r3vPdIOQ\nHu2lEIKeQghDAp+KCHoJKRDqqR4kInoxood66KkefO" +
       "ice7bducZtsLuz+32/7/t9v+873/ZP4DZ0\n4DOQYYiqshTAeQ0ZAfqXnRLR" +
       "BMKz5LjA7+5dfqrgzn0ncHCgTkZyEukGBm3cElyGwSwWpWBYlSTE\nYxJpOK" +
       "eDftuopolFBuzjMCO7+bQoCRj0c0V40AQFS/DgITjN1FuRiT7V5BKhQnOxJO" +
       "t1ia2rqfub\nTgAIolVTpXxKUnEBwXxC/hdz3PdX3cyno4rPjBl1gT+40Hny" +
       "Q+hrvYtS9miqIdKqMWjniiCmSKxg\nGc5pWk4jUvdRtQLUFrDUMnxziqwK4g" +
       "0RJiXEiQY+OOo///zHXa8pSY1E3mDgLVOa+tDKz/0/nPX+\nxCi4/e767y4z" +
       "qIO/CVaBwyTVYkXhRCWDBBq+cSBxLbq4dtpFxNBWamiDiOsZm6aGR1FK/Ken" +
       "VMo+\nG1QZ6JL0YPdx7NcaBVF2Ti1HUg7YgGeyeIXMC6qW1G+D41RVY5hM9N" +
       "H8TvQPx5ROqkKeZnaV5T9r\nnz+pQ4VPVyNgp9VoVZRzHjSLxhjSkCIgBUt5" +
       "6iDQSwEJGIPjZSMwAY30NNSGi0w1QLgeszpZsLvr\nPu28blt87wLOcdAgqV" +
       "AYhzxW9UlQj9M6MtKqJOS0KyMmhcYVD3l6yddJgrWW+JsBiWowiaQytpPA\n" +
       "kyZZwqqAOOCSYBKDZsZQgkoqyC4LGX395RMPBrWhcDgSmzXsO5MgpVcTc8gG" +
       "M03OMEUmXZazishD\nXFot3XvtDxt8gxfN7h6RmR81YdBqXVW2Vtg2sfput7" +
       "/iiEficpWxo/BT7Kp3VuyPmAR5NEHkRvoC\nv7X/9lZk6s4aGzsFyuiwdgms" +
       "i0qKaIdBk9XTMYiRQVP4MWjBIp9BmNSj8PlASBBIMw3T2HHvc2qD\n6B2PRC" +
       "PhWVILfdld+O1l5LoqyemiTDZVsaT1rs1vz/bjLYxeRlTIRu6tFOwQxjdFnJ" +
       "h8blO+HrsM\npvebwZ7t1fiXJGtwc8nfLDWiZOWhjY9oYKSRt0Si4fuoKLWC" +
       "miXLrSiG19SIKhdgyplEmv4CjtOQ\nruAGAAA="));
    
    Customer(String setups, String transports, String addr_a, int port_a,
             double maxPrice)
          throws Exception {
        super();
        boolean decided = false;
        int retry = 3;
        SJSessionParameters params =
          SJTransportUtils.createSJSessionParameters(setups, transports);
        final SJService c_ca = SJService.create(p_ca, addr_a, port_a);
        SJSocket s_ca = null;
        try {
            s_ca = c_ca.request(params);
            {
                LoopCondition loopCond$0 =
                  SJRuntime.negotiateOutsync(false, new SJSocket[] { s_ca });
                while (loopCond$0.call(!decided && retry-- > 0)) {
                    SJRuntime.send(TRAVEL_METHOD, s_ca);
                    double cost = SJRuntime.receiveDouble(s_ca);
                    System.out.println("Received quote: " + cost);
                    decided = cost < maxPrice;
                }
            }
            if (retry >= 0) {
                {
                    SJRuntime.outlabel("ACCEPT", s_ca);
                    System.out.println("Quote accepted.");
                    SJRuntime.send(ADDRESS, s_ca);
                    System.out.println("Received dispatch date: " +
                                       SJRuntime.receive(s_ca));
                }
            } else {
                {
                    SJRuntime.outlabel("REJECT", s_ca);
                    System.out.println("Quote rejected.");
                }
            }
        }
        finally {
            SJRuntime.close(s_ca);
        }
    }
    
    public static void main(String[] args) throws Exception {
        String setups = args[0];
        String transports = args[1];
        String host_a = args[2];
        int port_a = Integer.parseInt(args[3]);
        double maxPrice = Double.parseDouble(args[4]);
        new Customer(setups, transports, host_a, port_a, maxPrice);
    }
    
    final public static String jlc$CompilerVersion$jl = "2.3.0";
    final public static long jlc$SourceLastModified$jl = 1277831938000L;
    final public static String jlc$ClassType$jl =
      ("H4sIAAAAAAAAAJVYfWwcRxWfu7Pv/HGJfa4dUtux8+E0MWnOtGqhqaGJc7ax" +
       "3XPt3l3c1FU57+3O\n3a29t7vsztnnqKpSkJoQpCCUpB+IEoSKSlGRICUgAS" +
       "pUbQlQECJIboVo/wmCSrSI/EMjFApvZvbj\nbm9zESft3Ozsm3lv3sfvvZmX" +
       "PkDNpoHiJjZNWVOX42RdxyZrtdwyFokZT8/MC4aJpYQimGYGPmTF\npY/khR" +
       "/EvjAVRIFF1KVqY4osmJmioZULxUxRNisG2q5rynpB0Yi1Yt0a9+66vvbbEz" +
       "N9IdSxiDpk\nNU0EIosJTSW4QhZRtIRLOWyYY5KEpUUUUzGW0tiQBUU+BoSa" +
       "CoxNuaAKpGxgM4VNTVmlhF1mWccG\n42kPJlFU1FSTGGWRaIZJUGdyWVgVRs" +
       "pEVkaSsklGkyicl7EimZ9Hj6NgEjXnFaEAhFuS9i5G2Ioj\nk3QcyNtkENPI" +
       "CyK2pzStyKpE0KB3hrPjofuBAKZGSpgUNYdVkyrAAOriIimCWhhJE0NWC0Da" +
       "rJWB\nC0G9N1wUiFp0QVwRCjhL0FYv3Tz/BFStTC10CkE9XjK2Etis12OzKm" +
       "vNhaP/OTX/4XawOMgsYVGh\n8odh0oBnUgrnsYFVEfOJ18rxs9MPl/uDCAFx" +
       "j4eY04zt/vGR5Hs/H+Q0fT40c8wXs+L1T/Zvuzz2\nl9YQFaNF10yZukLNzp" +
       "lV560voxUdvHuLsyL9GLc//iL1y4ePfxf/PYhaplFY1JRySZ1GrViVElY/\n" +
       "Av2krGI+OpfPm5hMoyaFDYU19g7qyMsKpupohr4ukCLrV3TEfwF4clZ/E20I" +
       "ak+UTaKVsBE3lylx\nrELbzWuBAEjb740cBdxsSlMkbGTFF6785rGJ+790kt" +
       "uB+o7FDMwK4bOCCdhbFdfjNgcUCLBVb6H+\nxfc/ZhjCOvX7yhOXtz17SXgO" +
       "tAm7MuVjmAkdWGuiLUy6syEsJNygmoaeADbPit3H3+v92h9ffCOI\nQr7QkH" +
       "QGJzWjJCjUunYsdFnsvF/AKYa8runH+x+nZl/eePOdva6TEjRUFzv1M6nv7/" +
       "Rq3dBELAG2\nuMs//e+pf55pPnAxiJogoABSiACOAPE54OVREwOjNp7QvYSS" +
       "qD1ft/E2Agpaq9owbaOs32F5EMQG\nylhetJM2zG+qnAfM1e3ZA0Ora18Mf+" +
       "Ktn7W/wZRiA1tHFQKmMeFhEnNdJGNgDOPvPDN/5twHJx4J\nQWDqOnMLVAHK" +
       "j9UGFBVcog71/oXRztP7zR8x47fKpVKZCDkFAwALiqKtYSlLGALFqtCOgQzo" +
       "KJoD\nsALcyyqwENeCHlgFx/WBg/jW7rNPDX/9LQoYOlNTD0jWzARsZu9dgP" +
       "JsO5RHnCMqHR9mjVkPW/OG\nXAJUWLVg66sDz//15Supbu5KHNt31cFr9RyO" +
       "78wW7TpV0o5GHBj16/t2vPR46t0cx72uWqVOqOXS\n3effxsOHoqJPtIcgA7" +
       "Gd7gNm9D9OUFjSyqBu/r4rQNs9jnbsqLa0c4urnYmKiHUKiEz4fhr43a4r\n" +
       "AP6tcOtGh9OPziyd3Mm8gevaVanT7KfNnVyGAzejqjjiNTHSJuB9e0PQmaR5" +
       "2o1J+bF/PXHh9+ei\nQRRcRBHZnJRVQaG+Zz7AUcYnQ3mWOPbKkW9c+x15ly" +
       "nZDWsqWF+lHo4XhCrEuWdjNRb+/vlSEEUW\nUSerMgSVLAhKmYbPItQJZsIa" +
       "TKJNNd9rcz5PcKMObPV7fa2KrRdQXMeAPqWm/RYPhrTCcxc8HRaG\ndHgwJB" +
       "DQaecwmzDA2h3cyAGCIrohrwq0MEObMqmxhYlkdnYiMzU37thzM0JszmdqGP" +
       "bC02kx7PRn\nOEWbcYJaZ8eOZudT04kJn/gdZ359Yz73wROz+MT8+cxafCJj" +
       "4+OpiXTa5tJdkzWh2oRq0rQ5Geiu\nmzsjZAqiQfXgesXSl99+cPboQyM8ru" +
       "9ouMQDYDvJ45HnptWDr/RsqEGaG8LmcpV9gyYhaG/SXtJy\nDvrKe+mZNP/E" +
       "a7oqVf2X/z6iD1URfeHVSFdCK+lQwhjbP4vB8cHOkm6r7YivPzTnaZiBNzTp" +
       "WVGw\nVdnnbNQoq0QuYXp0sJTDNBpgkPQIqHXIoxNnA3G6AcIx+NLGpy+opP" +
       "8KC8yIdR6gRbmbu6BQU0CR\nrNIz0N6GqzpqyYp2ZcZzoliUFYlKloE1djW0" +
       "lpUcIukXHiqcfZ7a18J7mj9vc0HTlcscOqKWNEnO\nyywTAoxe79h9x8X3T3" +
       "dyUHezHSDfzRdwx289jI6/+bkPB9gyAZGukbWS4QGngNvTQCOJw7gg1ymE\n" +
       "euxtDWZVTbpXefrSt+evnrTVsMTS3nCDyXNlsgbKxn5MdzeYl9Q0nc9ZmfnW" +
       "4qsz15JceTlNWueG\nc/l/vDH/nAExVvQToJGuDvvOgpTTJZvjWIfDAVaJss" +
       "4LIfAoASZDjq3y1CnBLM4K+qgtqY5qKy7r\ne3PkT6++tmXpDyEUnERtiiZI" +
       "kwIrVVEr1IjYLMI5oKIfPMREiK61WMAahMV6HPnZgqA1IYeVKmmn\nUUsRuC" +
       "Q0CSqykCLkqCiHKrrxkxdboHgYSyQm5jNmY0ukYat+yru7wZxZ6AO+AsqUyq" +
       "osstM7X2Nw\nY+tzbUP77mHWbC9xukxNBuNGbRTZKSxip26rlopOX9OtmAB8" +
       "2uxqexxAjo7Os2/mV/5cOA8aSE3M\nTCQyVj21av2vwwqsWGG5ji2aZ+12Xm" +
       "Yht8w64FbI2250lGaF34mjV6NPCq8/aks5AvmPaPp+Ba9i\npa5iM9D+hqA0" +
       "yy4U3PQRSh8c3nN7+9/gnHKDI1jMGkxhUjZUR+HsbCb832ezQc9evfLEVvse" +
       "DBXl\nXwVZscLrm7p7ktpJo7VVTZtRKyhTWq9TAtBnDzwLlvUXfM9HdYksCC" +
       "bXobCQReiY7Pqpcelcf0PC\njtLc8y6Ehq4Gf7pliJ0Om3KAAJZOa6+W6m+O" +
       "ai6EmKxtzs5uhWfwJjsb9i3x76PN2UoAMed9ymf3\ntL9Im9OQyMMKVgv85o" +
       "KV5mfcyjzIyX0ODglFUzHNSk4xxb7JWty5qXOOI175TnL5GC+fGoPL5WsN\n" +
       "ZEdZo4/0/ZtQqIhURJ+KktfblYb2pq+fYiudgkqnBKcC74GraVWTJUb3verE" +
       "W6XAmhHQc4t9L0ML\nhq11d6T8Jk/ceXlp72t67Nfcl+zbtkgSteTLilJd7l" +
       "f1w7qB8zKTN8KLf539/ZCgaHWRS8cuMq/4\nTgX9D0AlEWIAFgAA");
}
