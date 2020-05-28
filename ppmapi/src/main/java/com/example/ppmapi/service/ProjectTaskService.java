package com.example.ppmapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ppmapi.domain.Backlog;
import com.example.ppmapi.domain.ProjectTask;
import com.example.ppmapi.exception.ProjectNotFoundException;
import com.example.ppmapi.repositories.BacklogRepository;
import com.example.ppmapi.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService { 
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
		try {
	
			//ProjectTasks to be added to specific project, project!=null, Backlog Exists
			Backlog backlog =  backlogRepository.findByProjectIdentifier(projectIdentifier);
			
			//Set the Backlog to project task
			projectTask.setBacklog(backlog);
			
			//We want our project Sequence to be like this. IDPRO-1  IDPRO-2  ...100 101
			Integer backLogSequence = backlog.getPTSequence();
			//Update the BacklogSequence
			backLogSequence++;
			backlog.setPTSequence(backLogSequence);
			//Add backlogSequence to ProjectTask
			projectTask.setProjectSequence(projectIdentifier+"-"+backLogSequence);
			projectTask.setProjectIdentifer(projectIdentifier);
			
			//Initial priority when priority is null
			if(projectTask.getPriority()==null) {
				projectTask.setPriority(3);
			}
			
			//INITIAL Status when status is null
			if(projectTask.getStatus()=="" || projectTask.getStatus()==null) {
				projectTask.setStatus("TO_DO");
			}

		}catch(Exception ex) {
			  throw new ProjectNotFoundException("project not found");
		}
		
		
		//Exceptions: Project not Found
		
			return projectTaskRepository.save(projectTask);
	}
	
	    public ProjectTask findPTByProjectSequence(String backlog_id,String sequence){
	
	    	Backlog backlog=backlogRepository.findByProjectIdentifier(backlog_id);
	    	if(backlog==null) {
	    		 throw new ProjectNotFoundException("project id does not exist");
	    	}
	    	ProjectTask projectTask=projectTaskRepository.findByProjectSequence(sequence);
	    	if(projectTask==null) {
	    		throw new ProjectNotFoundException("project task does not exist");
	    		
	    	}
	    	if(!projectTask.getProjectIdentifer().equals(backlog_id)) {
	    		throw new ProjectNotFoundException("project identifier not exist");
	    	}
	    	return projectTaskRepository.findByProjectSequence(sequence);
	    }
	    
	    public ProjectTask updateProjectTaskByProjectSequence(String backlog_id,String sequence) {
	    	
	          
	    	return projectTaskRepository.findByProjectSequence(sequence);
	      
	    }
       
}