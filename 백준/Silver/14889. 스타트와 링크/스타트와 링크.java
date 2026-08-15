import java.util.*;
import java.io.*;

public class Main {
    static int[][] map;
    static int[] startTeam;
    static int[] linkedTeam;
    static int[] startTeamPair;
    static int[] linkedTeamPair;

    static int startTeamPoint;
    static int LinkedTeamPoint;
    static int answer=Integer.MAX_VALUE;

    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());

        map=new int[N][N];
        startTeam=new int[N/2];
        linkedTeam=new int[N/2];
        startTeamPair=new int[2];
        linkedTeamPair=new int[2];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        recursiveTeam(0,0);

        System.out.println(answer);
    }

    public static void recursiveTeam(int start,int level){
        if(level==N/2){
            boolean[] val=new boolean[N+1];

            for(int i=0;i<N/2;i++){
                val[startTeam[i]]=true;
            }

            int c=0;
            for(int i=1;i<=N;i++){
                if(!val[i]){
                    linkedTeam[c]=i;
                    c+=1;
                }
            }

            startTeamPoint=0;
            LinkedTeamPoint=0;

            recursivePairStart(0,0);
            recursivePairLinked(0,0);

            int tmp=Math.abs(startTeamPoint-LinkedTeamPoint);
            answer=Math.min(tmp,answer);

            return;
        }

        for(int i=start;i<N;i++){
            startTeam[level]=i+1;
            recursiveTeam(i+1,level+1);
        }
    }

    public static void recursivePairStart(int start,int level){
        if(level==2){
            int y=startTeamPair[0];
            int x=startTeamPair[1];

            startTeamPoint+=map[y-1][x-1];
            startTeamPoint+=map[x-1][y-1];

            return;
        }

        for(int i=start;i<startTeam.length;i++){
            startTeamPair[level]=startTeam[i];
            recursivePairStart(i+1,level+1);
        }
    }

    public static void recursivePairLinked(int start,int level){
        if(level==2){
            int y=linkedTeamPair[0];
            int x=linkedTeamPair[1];

            LinkedTeamPoint+=map[y-1][x-1];
            LinkedTeamPoint+=map[x-1][y-1];

            return;
        }

        for(int i=start;i<linkedTeam.length;i++){
            linkedTeamPair[level]=linkedTeam[i];
            recursivePairLinked(i+1,level+1);
        }
    }
}

