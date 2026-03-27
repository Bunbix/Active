public class Cousins {
    public static void main(String[] args) {
        String[] bradKids = {"Maddy", "Ross", "Henry"};
        String[] jayKids = {"Brian", "Kevin", "Kristin"};
        String[] annKids = {};
        String[] libbyKids = {"Ellie", "Hanna", "Jack", "Ben"};
        String[][] cousins = {bradKids, jayKids, annKids, libbyKids}; 

       System.out.println("Cousins:");
for (String[] family : cousins) {
    for (String cousin : family) {
        System.out.print(cousin + " ");
    }
    System.out.println();
}
    } //end Main
} //end class Cousins