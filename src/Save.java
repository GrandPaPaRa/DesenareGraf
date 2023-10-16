import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.Arrays;
import java.awt.Point;
public class Save{
    private File file;
    private FileWriter writer;
    public Save(String fileName){
        file = new File(fileName);
        try{
            if(file.createNewFile())
                System.out.println("File created");
            else
                System.out.println("File exist");
        }catch (IOException exception){
            exception.fillInStackTrace();
        }
       /* try{
            writer = new FileWriter(fileName);
        }catch (IOException exception){
            exception.fillInStackTrace();
        }*/
    }

    private int pointToIndex(Point target, Vector<Node> list){ //index = number - 1
        for(Node it : list){
            if(target.equals(it.getPoint()))
                return it.getNumber() - 1;
        }
        return -1; //nu s-a gasit punctul
    }
    private void write(int[][] mat, int size){
        try(FileWriter writer = new FileWriter("adiacent.txt")){
            writer.write(Integer.toString(size) + "\n");
            for(int[] row : mat){
                for(int elem : row)
                    writer.write(Integer.toString(elem));
                writer.write("\n");
            }
            writer.close();
        }catch (IOException exception){
            exception.fillInStackTrace();
        }

    }
    public void saveNonOriented(Vector<Arc> arcList, Vector<Node> nodeList){
        int i,j;
        int[][] mat = new int[nodeList.size()][nodeList.size()];
        for(int[] row : mat)
            Arrays.fill(row, 0);
        for(Arc it : arcList){
            i = pointToIndex(it.getStart(), nodeList);
            j = pointToIndex(it.getEnd(), nodeList);
            mat[i][j] = mat[j][i] = 1;
        }

        write(mat,nodeList.size());
    }
}
