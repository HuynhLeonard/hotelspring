import axios from "axios";

export const api = axios.create({
    baseURL: "https://localhost:9192"
});

export const addRoom = async (photo, roomType, roomPrice) => {
    const formData = new FormData();

    formData.append("photo", photo);
    formData.append("roomType", roomType);
    formData.append("roomPrice", roomPrice);

    const response = await api.post("/room/add/new-rooms");

    if(response.status === 201) {
        return true;
    } else {
        return false;
    }
}

export const getAllRoomTypes = async () => {
    try {
        const response = await api.get("/room/room-types");
    } catch (error) {
        throw new Error("Error fetching room types.")
    }
}