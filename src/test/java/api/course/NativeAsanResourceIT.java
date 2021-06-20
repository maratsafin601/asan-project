package api.course;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeAsanResourceIT extends AsanResourceTests {

    // Execute the same tests but in native mode.
}