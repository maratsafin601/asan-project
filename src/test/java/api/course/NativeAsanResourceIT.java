package api.course;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeAsanResourceIT extends AsanResourceTest {

    // Execute the same tests but in native mode.
}