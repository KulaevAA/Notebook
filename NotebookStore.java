import java.util.*;

public class NotebookStore {
    private Set<Notebook> notebooks;

    public NotebookStore(Set<Notebook> notebooks) {
        this.notebooks = notebooks;
    }

    public Set<Notebook> filterNotebooks(Map<String, Object> criteria) {
        Set<Notebook> filteredNotebooks = new HashSet<>(notebooks);
        
        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            Iterator<Notebook> iterator = filteredNotebooks.iterator();
            switch (entry.getKey()) {
                case "ram":
                    int minRam = (int) entry.getValue();
                    while (iterator.hasNext()) {
                        Notebook notebook = iterator.next();
                        if (notebook.getRam() < minRam) {
                            iterator.remove();
                        }
                    }
                    break;
                case "hdd":
                    int minHdd = (int) entry.getValue();
                    while (iterator.hasNext()) {
                        Notebook notebook = iterator.next();
                        if (notebook.getHdd() < minHdd) {
                            iterator.remove();
                        }
                    }
                    break;
                case "os":
                    String os = (String) entry.getValue();
                    while (iterator.hasNext()) {
                        Notebook notebook = iterator.next();
                        if (!notebook.getOs().equalsIgnoreCase(os)) {
                            iterator.remove();
                        }
                    }
                    break;
                case "color":
                    String color = (String) entry.getValue();
                    while (iterator.hasNext()) {
                        Notebook notebook = iterator.next();
                        if (!notebook.getColor().equalsIgnoreCase(color)) {
                            iterator.remove();
                        }
                    }
                    break;
            }
        }
        
        return filteredNotebooks;
    }

    public void requestAndFilterNotebooks() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> criteria = new HashMap<>();
        boolean continueInput = true;
        while (continueInput) {
        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        System.out.println("5 - Бренд");
        System.out.println("0 - Завершить ввод критериев");
        int criteriaNumber = scanner.nextInt();
        switch (criteriaNumber) {
            case 1:
                System.out.print("Введите минимальное значение ОЗУ: ");
                criteria.put("ram", scanner.nextInt());
                break;
            case 2:
                System.out.print("Введите минимальное значение объема ЖД: ");
                criteria.put("hdd", scanner.nextInt());
                break;
            case 3:
                System.out.print("Введите операционную систему: ");
                criteria.put("os", scanner.next());
                break;
            case 4:
                System.out.print("Введите цвет: ");
                criteria.put("color", scanner.next());
                break;
            case 5:
                System.out.print("Введите бренд: ");
                criteria.put("brand", scanner.next());
                break;
            case 0:
                continueInput = false;
                break;
        }
        }

        Set<Notebook> filteredNotebooks = filterNotebooks(criteria);
        System.out.println("Ноутбуки, соответствующие критериям:");
        for (Notebook notebook : filteredNotebooks) {
            System.out.println(notebook);
        }
        
        scanner.close();
    }

    public static void main(String[] args) {
        Set<Notebook> notebooks = new HashSet<>(Arrays.asList(
            new Notebook("Dell", 32, 512, "Linux", "Black"),
            new Notebook("HP", 8, 1024, "Windows", "Silver"),
            new Notebook("Apple", 8, 256, "MacOS", "Gray"),
            new Notebook("Lenovo", 2, 512, "Windows", "Black"),
            new Notebook("Lenovo", 4, 512, "Windows", "Black"),
            new Notebook("Dell", 16, 512, "Windows", "Black"),
            new Notebook("Dell", 16, 512, "Windows", "White")
        ));

        NotebookStore store = new NotebookStore(notebooks);
        store.requestAndFilterNotebooks();
    }
}
