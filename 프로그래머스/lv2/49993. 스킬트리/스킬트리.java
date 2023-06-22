class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for ( var skillTree : skill_trees) {
            if (checkSkill(skill, skillTree)) answer++; 
        }
        
        return answer;
    }
    
    private boolean checkSkill(String skill, String skillTree) {
        int orderLength = skill.length();
        
        int[] order = new int[26];
        
        for (int i = 0; i < orderLength; i++) {
            order[skill.charAt(i) - 'A'] = i + 1;
        }
        
        int count = 1;
        
        int skillTreeLength = skillTree.length();
        
        int now;
        for (int i = 0; i < skillTreeLength; i++ ) {
            now = skillTree.charAt(i) - 'A';
            
            if ( order[now] > count ) return false;
            
            if ( order[now] != 0 )
                count++;
        }
    
        return true;
    }
}