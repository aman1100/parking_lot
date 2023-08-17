package controller;

import dtos.IssueTicketRequestDto;
import dtos.IssueTicketResponseDto;
import enums.ResponseStatus;
import models.Ticket;
import services.AuthService;
import services.TicketService;

public class TicketController {
    private TicketService ticketService;
    private AuthService authService;

    public TicketController(TicketService ticketService, AuthService authService){
        this.ticketService = ticketService;
        this.authService = authService;
    }

    public IssueTicketResponseDto issueTicket(IssueTicketRequestDto requestDto){
        if(authService.authenticate("token")){
            IssueTicketResponseDto issueTicketResponseDto = new IssueTicketResponseDto();
            Ticket ticket;
            try{
                ticket = ticketService.issueTicket(requestDto.getVehicleType(),
                        requestDto.getVehicleNumber(),
                        requestDto.getVehicleNumber(),
                        requestDto.getGateId());
            }catch (Exception e){
                issueTicketResponseDto.setResponseStatus(ResponseStatus.ERROR);
                issueTicketResponseDto.setErrorMessage(e.getMessage());
                return issueTicketResponseDto;
            }
            issueTicketResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            issueTicketResponseDto.setTicket(ticket);
            return issueTicketResponseDto;
        }
        return null;
    }
}
