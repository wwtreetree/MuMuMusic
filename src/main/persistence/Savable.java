package persistence;


import java.io.PrintWriter;

// Represents data that can be saved to file
public interface Savable {
    // EFFECTS: writes the saveable to printWriter
    void save();
}
