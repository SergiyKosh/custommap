import org.junit.jupiter.api.Test;
import ua.custom.map.impl.ImplCustomMap;

import static org.junit.jupiter.api.Assertions.*;

public class ImplCustomMapTest {
    @Test
    void isEmptyTest() {
        ImplCustomMap<String, String> map = new ImplCustomMap<>();
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
        assertFalse(map.entrySet().iterator().hasNext());
    }

    @Test
    void putTest() {
        ImplCustomMap<String, String> map = new ImplCustomMap<>();
        map.put("Test", "Test");
        assertFalse(map.isEmpty());
        assertTrue(map.containsKey("Test"));
        assertTrue(map.containsValue("Test"));
        assertTrue(map.entrySet().iterator().hasNext());
        assertEquals(1, map.size());
        assertEquals(1, map.entrySet().size());
        assertEquals(1, map.values().size());
        assertEquals(1, map.keySet().size());
        assertNotNull(map.get("Test"));
    }
}
