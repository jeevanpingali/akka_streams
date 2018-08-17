package pingali.jeevan.akka.akka_streams;

import akka.Done;
import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.stream.*;
import akka.stream.javadsl.*;

import java.util.concurrent.CompletionStage;

public class App {
    public static void main(String args[]) {
        final ActorSystem system = ActorSystem.create();
        final Materializer materializer = ActorMaterializer.create(system);

        final Source<Integer, NotUsed> source = Source.range(1, 100);
        final CompletionStage<Done> done = source.runForeach(i -> System.out.println(i), materializer);

        done.thenRun(() -> system.terminate());
    }
}
