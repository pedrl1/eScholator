import java.util.ArrayList;
import java.util.Random;

public class EnemQuestion {
    private ArrayList<Question> questions;
    private Random random;

    public EnemQuestion() {
        questions = new ArrayList<>();
        random = new Random();
        loadQuestions();
    }

    private void loadQuestions() {
        // ========== Difficulty 1 ==========
        // Attack
        String[] a1 = {"Stack", "Queue", "Array", "Tree"};
        questions.add(new Question("Which data structure uses LIFO?", a1, 0, 1, Question.QuestionType.ATTACK));
        String[] a2 = {"O(log n)", "O(n)", "O(n log n)", "O(1)"};
        questions.add(new Question("What is the time complexity of binary search?", a2, 0, 1, Question.QuestionType.ATTACK));
        String[] a3 = {"TCP", "UDP", "HTTP", "FTP"};
        questions.add(new Question("Which protocol guarantees delivery?", a3, 0, 1, Question.QuestionType.ATTACK));
        // Defense
        String[] d1 = {"CPU", "RAM", "SSD", "GPU"};
        questions.add(new Question("Which component is volatile memory?", d1, 1, 1, Question.QuestionType.DEFENSE));
        String[] d2 = {"Java", "Python", "C++", "Assembly"};
        questions.add(new Question("Which language is interpreted by the JVM?", d2, 0, 1, Question.QuestionType.DEFENSE));
        String[] d3 = {"Compiler", "Interpreter", "Assembler", "Linker"};
        questions.add(new Question("What translates whole source code into machine code at once?", d3, 0, 1, Question.QuestionType.DEFENSE));
        // Counterattack
        String[] c1 = {"extends", "implements", "inherits", "using"};
        questions.add(new Question("Which keyword is used to inherit a class in Java?", c1, 0, 1, Question.QuestionType.COUNTERATTACK));
        String[] c2 = {"public", "private", "protected", "static"};
        questions.add(new Question("Which access modifier restricts visibility to the same class only?", c2, 1, 1, Question.QuestionType.COUNTERATTACK));
        String[] c3 = {"boolean", "int", "String", "void"};
        questions.add(new Question("Which Java keyword indicates that a method returns nothing?", c3, 3, 1, Question.QuestionType.COUNTERATTACK));

        // ========== Difficulty 2 ==========
        String[] a4 = {"Heap", "Stack", "Queue", "Graph"};
        questions.add(new Question("Memory for dynamically allocated objects usually comes from the...", a4, 0, 2, Question.QuestionType.ATTACK));
        String[] a5 = {"Polymorphism", "Inheritance", "Encapsulation", "Abstraction"};
        questions.add(new Question("What OOP principle allows a method to take many forms?", a5, 0, 2, Question.QuestionType.ATTACK));
        String[] a6 = {"192.168.1.1", "255.255.255.0", "127.0.0.1", "0.0.0.0"};
        questions.add(new Question("Which IP address is the loopback address?", a6, 2, 2, Question.QuestionType.ATTACK));
        String[] d4 = {"Normalization", "Denormalization", "Indexing", "Partitioning"};
        questions.add(new Question("What database process reduces data redundancy?", d4, 0, 2, Question.QuestionType.DEFENSE));
        String[] d5 = {"Thread", "Process", "Daemon", "Scheduler"};
        questions.add(new Question("The smallest unit of execution managed by the OS is a...", d5, 0, 2, Question.QuestionType.DEFENSE));
        String[] d6 = {"SQL Injection", "XSS", "CSRF", "Phishing"};
        questions.add(new Question("What attack exploits unsanitized user input in a query?", d6, 0, 2, Question.QuestionType.DEFENSE));
        String[] c4 = {"HashMap", "ArrayList", "LinkedList", "TreeMap"};
        questions.add(new Question("Which collection stores key-value pairs?", c4, 0, 2, Question.QuestionType.COUNTERATTACK));
        String[] c5 = {"try-catch", "if-else", "switch-case", "while-do"};
        questions.add(new Question("Which Java construct handles exceptions?", c5, 0, 2, Question.QuestionType.COUNTERATTACK));
        String[] c6 = {"Big-O", "NP-hard", "Halting Problem", "P vs NP"};
        questions.add(new Question("What notation is used to describe algorithmic complexity?", c6, 0, 2, Question.QuestionType.COUNTERATTACK));

        // ========== Difficulty 3 ==========
        String[] a7 = {"Deadlock", "Race Condition", "Starvation", "Livelock"};
        questions.add(new Question("When two threads wait for each other's locks indefinitely, it's a...", a7, 0, 3, Question.QuestionType.ATTACK));
        String[] a8 = {"ACID", "BASE", "CAP", "SOLID"};
        questions.add(new Question("Which properties guarantee reliable database transactions?", a8, 0, 3, Question.QuestionType.ATTACK));
        String[] a9 = {"Dijkstra", "Bellman-Ford", "A*", "Kruskal"};
        questions.add(new Question("Which algorithm finds the shortest path in a weighted graph with non-negative edges?", a9, 0, 3, Question.QuestionType.ATTACK));
        String[] d7 = {"Virtual Memory", "Cache Memory", "Flash Storage", "Register File"};
        questions.add(new Question("Which technique allows a program to use more memory than physically available?", d7, 0, 3, Question.QuestionType.DEFENSE));
        String[] d8 = {"Dependency Injection", "Singleton", "Observer", "Factory"};
        questions.add(new Question("Which design pattern provides loose coupling by passing dependencies from outside?", d8, 0, 3, Question.QuestionType.DEFENSE));
        String[] d9 = {"TCP Handshake", "TLS Handshake", "DNS Lookup", "ARP Request"};
        questions.add(new Question("What process uses SYN, SYN-ACK, ACK to establish a connection?", d9, 0, 3, Question.QuestionType.DEFENSE));
        String[] c7 = {"JVM", "JRE", "JDK", "JIT"};
        questions.add(new Question("The Just-In-Time compiler is part of the...", c7, 3, 3, Question.QuestionType.COUNTERATTACK));
        String[] c8 = {"Git Merge", "Git Rebase", "Git Cherry-Pick", "Git Stash"};
        questions.add(new Question("Which Git command integrates changes from one branch and replays commits on top?", c8, 1, 3, Question.QuestionType.COUNTERATTACK));
        String[] c9 = {"RAID 0", "RAID 1", "RAID 5", "RAID 10"};
        questions.add(new Question("Which RAID level requires at least three disks and uses distributed parity?", c9, 2, 3, Question.QuestionType.COUNTERATTACK));

        // ========== Difficulty 4 ==========
        String[] a10 = {"Segmentation Fault", "Stack Overflow", "Null Pointer", "Memory Leak"};
        questions.add(new Question("Accessing memory that does not belong to a process causes a...", a10, 0, 4, Question.QuestionType.ATTACK));
        String[] a11 = {"Microkernel", "Monolithic Kernel", "Hybrid Kernel", "Exokernel"};
        questions.add(new Question("Linux uses a...", a11, 1, 4, Question.QuestionType.ATTACK));
        String[] a12 = {"CDN", "DNS", "DHCP", "NAT"};
        questions.add(new Question("Which service translates domain names to IP addresses?", a12, 1, 4, Question.QuestionType.ATTACK));
        String[] d10 = {"Two-Phase Commit", "Three-Phase Commit", "Paxos", "Raft"};
        questions.add(new Question("Which protocol ensures atomicity in distributed transactions?", d10, 0, 4, Question.QuestionType.DEFENSE));
        String[] d11 = {"Cache Coherence", "Memory Consistency", "Bus Snooping", "MESI Protocol"};
        questions.add(new Question("What problem arises when multiple caches store the same memory location?", d11, 0, 4, Question.QuestionType.DEFENSE));
        String[] d12 = {"B-Tree", "Hash Index", "Bitmap Index", "Inverted Index"};
        questions.add(new Question("Which index structure is commonly used in relational databases?", d12, 0, 4, Question.QuestionType.DEFENSE));
        String[] c10 = {"Fork", "Clone", "Exec", "Wait"};
        questions.add(new Question("Which system call creates a new process by duplicating the current one?", c10, 0, 4, Question.QuestionType.COUNTERATTACK));
        String[] c11 = {"Garbage Collector", "Reference Counting", "Mark and Sweep", "All of the above"};
        questions.add(new Question("Which memory management technique automatically frees unused objects in Java?", c11, 3, 4, Question.QuestionType.COUNTERATTACK));
        String[] c12 = {"Public Key Infrastructure", "Symmetric Encryption", "Hashing", "Steganography"};
        questions.add(new Question("SSL/TLS relies on...", c12, 0, 4, Question.QuestionType.COUNTERATTACK));

        // ========== Difficulty 5 ==========
        String[] a13 = {"Lambda Calculus", "Turing Machine", "Finite Automaton", "Pushdown Automaton"};
        questions.add(new Question("What formal model is equivalent to a modern computer in computational power?", a13, 1, 5, Question.QuestionType.ATTACK));
        String[] a14 = {"CAP Theorem", "PACELC Theorem", "BASE", "ACID"};
        questions.add(new Question("The theorem that states a distributed system can only provide two of Consistency, Availability, Partition Tolerance is...", a14, 0, 5, Question.QuestionType.ATTACK));
        String[] a15 = {"Hadoop", "Spark", "Flink", "Storm"};
        questions.add(new Question("Which big data framework uses Resilient Distributed Datasets (RDDs)?", a15, 1, 5, Question.QuestionType.ATTACK));
        String[] d13 = {"Branch Prediction", "Speculative Execution", "Out-of-Order Execution", "Pipelining"};
        questions.add(new Question("Which CPU feature allows executing instructions before knowing if they are needed?", d13, 1, 5, Question.QuestionType.DEFENSE));
        String[] d14 = {"Gossip Protocol", "Consistent Hashing", "Vector Clocks", "Quorum"};
        questions.add(new Question("Dynamo-style databases use... to detect conflicts.", d14, 2, 5, Question.QuestionType.DEFENSE));
        String[] d15 = {"Zero-Day", "Buffer Overflow", "Ransomware", "Man-in-the-Middle"};
        questions.add(new Question("A vulnerability unknown to the software vendor is called a...", d15, 0, 5, Question.QuestionType.DEFENSE));
        String[] c13 = {"LLVM", "GCC", "JVM", "CLR"};
        questions.add(new Question("Which compiler infrastructure supports multiple front-ends and back-ends?", c13, 0, 5, Question.QuestionType.COUNTERATTACK));
        String[] c14 = {"Semaphore", "Mutex", "Monitor", "Spinlock"};
        questions.add(new Question("Which synchronization primitive allows multiple threads to access a limited resource pool?", c14, 0, 5, Question.QuestionType.COUNTERATTACK));
        String[] c15 = {"Kubernetes", "Docker Swarm", "Mesos", "Nomad"};
        questions.add(new Question("What is the most popular container orchestration platform?", c15, 0, 5, Question.QuestionType.COUNTERATTACK));
    }

    public ArrayList<Question> getQuestions() { return questions; }
    public Question getRandomQuestion() {
        if (questions.isEmpty()) return null;
        return questions.get(random.nextInt(questions.size()));
    }
}