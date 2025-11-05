package netologi.sorts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AviaSoulsTest {

    @Test
    public void testTicketCompareTo() {
        Ticket t1 = new Ticket("LED", "MOW", 3000, 1000, 1130);
        Ticket t2 = new Ticket("LED", "MOW", 2500, 900, 1030);
        assertTrue(t1.compareTo(t2) > 0);
        assertTrue(t2.compareTo(t1) < 0);
        assertEquals(0, t1.compareTo(t1));
    }

    @Test
    public void testTicketTimeComparator() {
        Ticket t1 = new Ticket("LED", "MOW", 3000, 1000, 1130); // 90 мин
        Ticket t2 = new Ticket("LED", "MOW", 2500, 900, 1020);   // 80 мин
        TicketTimeComparator comp = new TicketTimeComparator();
        assertTrue(comp.compare(t1, t2) > 0);
        assertTrue(comp.compare(t2, t1) < 0);
        assertEquals(0, comp.compare(t1, t1));
    }

    @Test
    public void testSearchByRoute() {
        AviaSouls manager = new AviaSouls();
        manager.add(new Ticket("LED", "MOW", 4000, 1200, 1330));
        manager.add(new Ticket("MOW", "LED", 3500, 1400, 1530));
        manager.add(new Ticket("LED", "MOW", 3000, 1000, 1130));

        Ticket[] result = manager.search("LED", "MOW");
        assertEquals(2, result.length);
        assertEquals(3000, result[0].getPrice());
        assertEquals(4000, result[1].getPrice());
    }

    @Test
    public void testSearchAndSortByTime() {
        AviaSouls manager = new AviaSouls();
        manager.add(new Ticket("LED", "MOW", 4000, 1200, 1400)); // 120 мин
        manager.add(new Ticket("LED", "MOW", 3000, 1000, 1130)); // 90 мин
        manager.add(new Ticket("MOW", "LED", 3500, 1400, 1530));

        TicketTimeComparator comp = new TicketTimeComparator();
        Ticket[] result = manager.searchAndSortBy("LED", "MOW", comp);
        assertEquals(2, result.length);
        assertEquals(90, result[0].getFlightDurationInMinutes());
        assertEquals(120, result[1].getFlightDurationInMinutes());
    }
}