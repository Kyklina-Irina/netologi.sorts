import java.util.Comparator;

public class TicketTimeComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket t1, Ticket t2) {
        int duration1 = calculateDuration(t1);
        int duration2 = calculateDuration(t2);
        return Integer.compare(duration1, duration2);
    }

    private int calculateDuration(Ticket ticket) {
        int fromHours = ticket.getTimeFrom() / 100;
        int fromMinutes = ticket.getTimeFrom() % 100;
        int toHours = ticket.getTimeTo() / 100;
        int toMinutes = ticket.getTimeTo() % 100;

        int fromTotalMinutes = fromHours * 60 + fromMinutes;
        int toTotalMinutes = toHours * 60 + toMinutes;

        return toTotalMinutes - fromTotalMinutes;
    }
}