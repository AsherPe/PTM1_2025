package test;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class TopicManagerSingleton {
    public static class TopicManager {
        private final ConcurrentHashMap<String, Topic> topics = new ConcurrentHashMap<>();

        public synchronized Topic getTopic(String name) {
            if(!topics.containsKey(name)) {
                topics.put(name, new Topic(name));
            }
            return topics.computeIfAbsent(name, Topic::new);
        }

        public Collection<Topic> getTopics() {
            return topics.values();
        }

        public void clear() {
            topics.clear();
        }
    }

    private static class Holder {
        private static final TopicManager INSTANCE = new TopicManager();
    }

    public static TopicManager get() {
        return Holder.INSTANCE;
    }
}