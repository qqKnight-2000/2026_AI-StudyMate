from typing import Any

from pydantic import BaseModel, Field


class CreateCaptureTaskRequest(BaseModel):
    rawShareText: str = Field(min_length=1)


class ApiResponse(BaseModel):
    code: str
    message: str
    data: Any
    traceId: str
    timestamp: str
