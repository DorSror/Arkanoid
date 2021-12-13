/**
 * @author Dor Sror - 207271875
 * The hit notifier interface - holds 2 methods.
 */
public interface HitNotifier {
    /**
     * Adds a hit listener to hit events.
     * @param hl the hit listener we wish to add.
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a hit listener to hit events.
     * @param hl the hit listener we wish to remove.
     */
    void removeHitListener(HitListener hl);
}